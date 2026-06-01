const STATUS_CONFIG={
  TOTAL:     { label: 'Total',     color: 'bg-gray-100 text-gray-700' },
  APPLIED:   { label: 'Applied',   color: 'bg-blue-100 text-blue-700' },
  INTERVIEW: { label: 'Interview', color: 'bg-yellow-100 text-yellow-700' },
  OFFER:     { label: 'Offer',     color: 'bg-green-100 text-green-700' },
  REJECTED:  { label: 'Rejected',  color: 'bg-red-100 text-red-700' },
}

const StatusBar = ({jobs}) =>{
    const counts= {
    TOTAL:     jobs.length,
    APPLIED:   jobs.filter(j => j.jobStatus === 'APPLIED').length,
    INTERVIEW: jobs.filter(j => j.jobStatus === 'INTERVIEW').length,
    OFFER:     jobs.filter(j => j.jobStatus === 'OFFER').length,
    REJECTED:  jobs.filter(j => j.jobStatus === 'REJECTED').length,
    }

    return (
    <div className="grid grid-cols-2 sm:grid-cols-5 gap-4 px-6 py-4">
      {Object.entries(STATUS_CONFIG).map(([key, { label, color }]) => (
        <div key={key} className={`rounded-xl p-4 ${color} flex flex-col items-center`}>
          <span className="text-2xl font-bold">{counts[key]}</span>
          <span className="text-xs font-medium mt-1">{label}</span>
        </div>
      ))}
    </div>
  )
}

export default StatsBar