import { useState, useEffect, useCallback } from 'react'
import Navbar from '../components/Navbar'
import StatsBar from '../components/StatsBar'
import JobCard from '../components/JobCard'
import JobModal from '../components/JobModal'
import ConfirmModal from '../components/ConfirmModal'
import {
  getAllJobs,
  createJob,
  updateJob,
  deleteJob,
  searchByCompany,
  searchByStatus,
} from '../api/jobApi'

const STATUSES = ['ALL', 'APPLIED', 'INTERVIEW', 'OFFER', 'REJECTED']

const Dashboard = () => {
  const [jobs, setJobs] = useState([])
  const [filteredJobs, setFilteredJobs] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState('')

  // Modal states
  const [jobModalOpen, setJobModalOpen] = useState(false)
  const [confirmModalOpen, setConfirmModalOpen] = useState(false)
  const [editingJob, setEditingJob] = useState(null)
  const [deletingJob, setDeletingJob] = useState(null)

  // Filter states
  const [searchQuery, setSearchQuery] = useState('')
  const [statusFilter, setStatusFilter] = useState('ALL')

  // Fetch all jobs
  const fetchJobs = useCallback(async () => {
    setLoading(true)
    setError('')
    try {
      const res = await getAllJobs()
      setJobs(res.data.data)
      setFilteredJobs(res.data.data)
    } catch (err) {
      setError('Failed to load jobs')
    } finally {
      setLoading(false)
    }
  }, [])

  useEffect(() => {
    fetchJobs()
  }, [fetchJobs])

  // Client-side filtering
  useEffect(() => {
    let result = [...jobs]
    if (statusFilter !== 'ALL') {
      result = result.filter(j => j.jobStatus === statusFilter)
    }
    if (searchQuery.trim()) {
      result = result.filter(j =>
        j.companyName.toLowerCase().includes(searchQuery.toLowerCase())
      )
    }
    setFilteredJobs(result)
  }, [searchQuery, statusFilter, jobs])

  // Add or Edit job
  const handleJobSubmit = async (data) => {
    try {
      if (editingJob) {
        await updateJob(editingJob.job_id, data)
      } else {
        await createJob(data)
      }
      setJobModalOpen(false)
      setEditingJob(null)
      fetchJobs()
    } catch (err) {
      setError('Failed to save job')
    }
  }

  // Open edit modal
  const handleEdit = (job) => {
    setEditingJob(job)
    setJobModalOpen(true)
  }

  // Open delete confirm
  const handleDeleteClick = (job) => {
    setDeletingJob(job)
    setConfirmModalOpen(true)
  }

  // Confirm delete
  const handleDeleteConfirm = async () => {
    try {
      await deleteJob(deletingJob.job_id)
      setConfirmModalOpen(false)
      setDeletingJob(null)
      fetchJobs()
    } catch (err) {
      setError('Failed to delete job')
    }
  }

  return (
    <div className="min-h-screen bg-gray-50">
      <Navbar />

      {/* Stats */}
      <StatsBar jobs={jobs} />

      {/* Toolbar */}
      <div className="px-6 py-4 flex flex-col sm:flex-row gap-3 items-start sm:items-center justify-between">
        {/* Search */}
        <input
          type="text"
          value={searchQuery}
          onChange={e => setSearchQuery(e.target.value)}
          placeholder="Search by company name..."
          className="w-full sm:w-72 border border-gray-300 rounded-lg px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500"
        />

        <div className="flex gap-3 items-center w-full sm:w-auto">
          {/* Status Filter */}
          <select
            value={statusFilter}
            onChange={e => setStatusFilter(e.target.value)}
            className="border border-gray-300 rounded-lg px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500"
          >
            {STATUSES.map(s => (
              <option key={s} value={s}>{s}</option>
            ))}
          </select>

          {/* Add Job Button */}
          <button
            onClick={() => {
              setEditingJob(null)
              setJobModalOpen(true)
            }}
            className="bg-blue-600 hover:bg-blue-700 text-white text-sm font-medium px-4 py-2 rounded-lg transition whitespace-nowrap"
          >
            + Add Job
          </button>
        </div>
      </div>

      {/* Error */}
      {error && (
        <p className="text-red-500 text-sm text-center py-2">{error}</p>
      )}

      {/* Job Cards */}
      <div className="px-6 pb-10">
        {loading ? (
          <div className="flex justify-center items-center py-20">
            <div className="w-8 h-8 border-4 border-blue-600 border-t-transparent rounded-full animate-spin" />
          </div>
        ) : filteredJobs.length === 0 ? (
          <div className="text-center py-20 text-gray-400">
            <p className="text-lg font-medium">No jobs found</p>
            <p className="text-sm mt-1">Add your first job application to get started</p>
          </div>
        ) : (
          <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-4">
            {filteredJobs.map(job => (
              <JobCard
                key={job.job_id}
                job={job}
                onEdit={handleEdit}
                onDelete={handleDeleteClick}
              />
            ))}
          </div>
        )}
      </div>

      {/* Modals */}
      <JobModal
        isOpen={jobModalOpen}
        onClose={() => {
          setJobModalOpen(false)
          setEditingJob(null)
        }}
        onSubmit={handleJobSubmit}
        editingJob={editingJob}
      />

      <ConfirmModal
        isOpen={confirmModalOpen}
        onClose={() => {
          setConfirmModalOpen(false)
          setDeletingJob(null)
        }}
        onConfirm={handleDeleteConfirm}
        jobName={deletingJob?.companyName}
      />
    </div>
  )
}

export default Dashboard