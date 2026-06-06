import { useEffect } from "react";
import { useForm } from "react-hook-form";


const STATUSES = ['APPLIED', 'INTERVIEW', 'OFFER', 'REJECTED']

const JobModal= ({isOpen, onClose, onSubmit, editingJob}) =>{
    const{register, handleSubmit, reset, formState:{errors}}= useForm();

    useEffect(() =>{
        if(editingJob){
            reset(editingJob)
        }
        else{
            reset({
                companyName:'',
                role:'',
                jobStatus:'APPLIED',
                appliedDate:'',
                interviewDate:'',
                notes:'',

            })
        }

    },[isOpen,reset,editingJob])

    if(!isOpen) return null

    return (
    <div className="fixed inset-0 z-50 flex items-center justify-center bg-black/40">
      <div className="bg-white rounded-2xl shadow-xl w-full max-w-md p-6 mx-4">
        {/* Header */}
        <div className="flex items-center justify-between mb-6">
          <h2 className="text-lg font-bold text-gray-900">
            {editingJob ? 'Edit Job' : 'Add New Job'}
          </h2>
          <button
            onClick={onClose}
            className="text-gray-400 hover:text-gray-600 text-xl font-bold"
          >
            ×
          </button>
        </div>

        <form onSubmit={handleSubmit(onSubmit)} className="space-y-4">
          {/* Company Name */}
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">
              Company Name
            </label>
            <input
              {...register('companyName', { required: 'Company name is required' })}
              className="w-full border border-gray-300 rounded-lg px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="Google"
            />
            {errors.companyName && (
              <p className="text-red-500 text-xs mt-1">{errors.companyName.message}</p>
            )}
          </div>

          {/* Role */}
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">Role</label>
            <input
              {...register('role', { required: 'Role is required' })}
              className="w-full border border-gray-300 rounded-lg px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="Software Engineer"
            />
            {errors.role && (
              <p className="text-red-500 text-xs mt-1">{errors.role.message}</p>
            )}
          </div>

          {/* Status */}
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">Status</label>
            <select
              {...register('jobStatus', { required: 'Status is required' })}
              className="w-full border border-gray-300 rounded-lg px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500"
            >
              {STATUSES.map(s => (
                <option key={s} value={s}>{s}</option>
              ))}
            </select>
          </div>

          {/* Applied Date */}
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">
              Applied Date
            </label>
            <input
              type="date"
              {...register('appliedDate', { required: 'Applied date is required' })}
              className="w-full border border-gray-300 rounded-lg px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500"
            />
            {errors.appliedDate && (
              <p className="text-red-500 text-xs mt-1">{errors.appliedDate.message}</p>
            )}
          </div>

          {/* Interview Date */}
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">
              Interview Date <span className="text-gray-400 font-normal">(optional)</span>
            </label>
            <input
              type="date"
              {...register('interviewDate')}
              className="w-full border border-gray-300 rounded-lg px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500"
            />
          </div>

          {/* Notes */}
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">
              Notes <span className="text-gray-400 font-normal">(optional)</span>
            </label>
            <textarea
              {...register('notes')}
              rows={3}
              className="w-full border border-gray-300 rounded-lg px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500 resize-none"
              placeholder="Any notes about this application..."
            />
          </div>

          {/* Buttons */}
          <div className="flex gap-3 pt-2">
            <button
              type="button"
              onClick={onClose}
              className="flex-1 text-sm font-medium text-gray-600 border border-gray-300 rounded-lg py-2 hover:bg-gray-50 transition"
            >
              Cancel
            </button>
            <button
              type="submit"
              className="flex-1 text-sm font-medium text-white bg-blue-600 rounded-lg py-2 hover:bg-blue-700 transition"
            >
              {editingJob ? 'Save Changes' : 'Add Job'}
            </button>
          </div>
        </form>
      </div>
    </div>
  )
}

export default JobModal

