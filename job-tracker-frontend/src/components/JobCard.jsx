const STATS={
    APPLIED:"bg-blue-100 text-blue-700",
    INTERVIEW:"bg-yellow-100 text-yellow-700",
    OFFER:"bg-green-100 text-green-700",
    REJECTED:"bg-red-100 text-red-700",
}

const JobCard=({job, onEdit, onDelete}) =>{
    return(
        <div className="bg-white rounded-xl shadow-sm border border-gray-300 flex flex-col gap-3 hover:shadow-md transition">
        <div className="flex items-start justify-between">
            <div>
                <h3 className="font-semibold text-gray-900 text-base">{job.companyName}</h3>
                <p className="text-sm text-gray-500 mt-0.5">{job.role}</p>
            </div>
           <span className={`text-xs font-semibold rounded-full ${STATS[job.jobStatus]}`}>{job.jobStatus}</span>
        </div>
        <div className="flex flex-col gap-1 text-sm text-gray-400">
            <span>Applied:
                <span className="text-gray-600">
                {job.appliedDate}
                </span>
             </span>
            {job.interviewDate && (<span>Interview:
                <span className="text-gray-600">
                 {job.interviewDate}
                </span>

            </span>)}
        </div>

        {job.notes && (
            <p className="bg-gray-50 text-sm rounded-lg text-gray-500 px-3 py-2 line-clamp-2">
                {job.notes}
            </p>
        )}
        <div className="flex gap-2 pt-1">
            <button onClick={onEdit} className="flex-1 text-xs font-medium text-blue-600 border border-blue-200 rounded-lg py-1.5 hover:bg-blue-50 transition">
                Edit
            </button>
            <button onClick={onDelete} className="flex-1 text-xs font-medium text-red-600 border border-red-200 rounded-lg py-1.5 hover:bg-red-50 transition">
                Delete
            </button>
        </div>
        
        </div>
    )
}
export default JobCard