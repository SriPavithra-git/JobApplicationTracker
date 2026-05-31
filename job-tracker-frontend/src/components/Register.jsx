import { useForm } from "react-hook-form";
import { useNavigate, Link } from "react-router-dom";
import api from '../api/axios';
import { useState } from "react";

const Register= () =>{
    const{handleSubmit, register, formState:{errors}}= useForm();
    const navigate= useNavigate();
    const[error, setError]=useState('')
    const[loading, setLoading]=useState(false)

    const onSubmit= async (data) =>{
        setError('')
        setLoading(true)
        try{
            await api.post("/user/register", data)
            navigate("/login")
        }
        catch(err){
           setError(err.response?.data?.message || 'Registration failed')
        }
        finally{
            setLoading(false)
        }
    }
    return(
        <div className="min-h-screen bg-gray-50 flex items-center justify-center">
            <div className="bg-white w-full max-w-md rounded-2xl shadow-md p-8">
                <div className="mb-8 text-center">
                    <h1 className="text-2xl font-bold text-gray-900">Create Account</h1>
                    <p className="text-sm mt-1 text-gray-500">Start tracking your job applications!!</p>
                </div>

                <form onSubmit={handleSubmit(onSubmit) } className="space-y-5">
                    <div>
                        <label className="block font-medium text-sm text-gray-700 mb-1" >Username</label>
                        <input 
                          {...register('username', {required:'username is required'})}
                          className="input-field"
                          placeholder="John"
                        />
                        {error && <p className="text-red-600 text-sm mt-1">{errors.username.message}</p>}
                    </div>
                   <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">Email</label>
            <input
              type="email"
              {...register('email', { required: 'Email is required' })}
              className="w-full border border-gray-300 rounded-lg px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="you@example.com"
            />
            {errors.email && <p className="text-red-500 text-xs mt-1">{errors.email.message}</p>}
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">Password</label>
            <input
              type="password"
              {...register('password', {
                required: 'Password is required',
                minLength: { value: 6, message: 'Minimum 6 characters' }
              })}
              className="w-full border border-gray-300 rounded-lg px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="••••••••"
            />
            {errors.password && <p className="text-red-500 text-xs mt-1">{errors.password.message}</p>}
          </div>

          {error && <p className="text-red-500 text-sm text-center">{error}</p>}

          <button
            type="submit"
            disabled={loading}
            className="w-full bg-blue-600 hover:bg-blue-700 text-white font-medium py-2 rounded-lg text-sm transition disabled:opacity-50"
          >
            {loading ? 'Creating account...' : 'Create Account'}
          </button>
        </form>

        <p className="text-center text-sm text-gray-500 mt-6">
          Already have an account?{' '}
          <Link to="/login" className="text-blue-600 hover:underline font-medium">
            Sign in
          </Link>
        </p>
      </div>
    </div>
  )
}

export default Register