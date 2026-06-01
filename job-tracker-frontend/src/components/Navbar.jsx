import { useAuth } from '../context/AuthContext'
import { useNavigate } from 'react-router-dom';

const Navbar  =() =>{
    const { logout }= useAuth();
    const navigate = useNavigate();

    const handleLogout= () =>{
        logout()
        navigate('/login')
    }

    return (
    <nav className="bg-white border-b border-gray-200 px-6 py-4 flex items-center justify-between shadow-sm">
      <div className="flex items-center gap-2">
        <div className="w-8 h-8 bg-blue-600 rounded-lg flex items-center justify-center">
          <span className="text-white font-bold text-sm">JT</span>
        </div>
        <span className="text-lg font-bold text-gray-900">Job Tracker</span>
      </div>

      <button
        onClick={handleLogout}
        className="text-sm text-gray-500 hover:text-red-500 font-medium transition"
      >
        Logout
      </button>
    </nav>
  )

}
export default Navbar
