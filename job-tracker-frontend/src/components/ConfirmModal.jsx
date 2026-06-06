const ConfirmModal = ({ isOpen, onClose, onConfirm, jobName }) => {
  if (!isOpen) return null

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center bg-black/40">
      <div className="bg-white rounded-2xl shadow-xl w-full max-w-sm p-6 mx-4">
        <h2 className="text-lg font-bold text-gray-900 mb-2">Delete Job</h2>
        <p className="text-sm text-gray-500 mb-6">
          Are you sure you want to delete{' '}
          <span className="font-semibold text-gray-700">{jobName}</span>?
          This action cannot be undone.
        </p>
        <div className="flex gap-3">
          <button
            onClick={onClose}
            className="flex-1 text-sm font-medium text-gray-600 border border-gray-300 rounded-lg py-2 hover:bg-gray-50 transition"
          >
            Cancel
          </button>
          <button
            onClick={onConfirm}
            className="flex-1 text-sm font-medium text-white bg-red-500 rounded-lg py-2 hover:bg-red-600 transition"
          >
            Delete
          </button>
        </div>
      </div>
    </div>
  )
}

export default ConfirmModal