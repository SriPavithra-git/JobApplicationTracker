import { createContext, useContext, useState, useEffect} from 'react'

const Authcontext = createContext(null)

export const AuthProvider=({children}) => {
    const[token, setToken]=useState(localStorage.getItem('token'))
    const[isAuthenticated, setIsAuthenticated]= useState(!!localStorage.getItem('token'))

    const login= (jwtToken) =>{
        localStorage.setItem('token', jwtToken)
        setToken(jwtToken)
        setIsAuthenticated(true)
    }

    const logout= () =>{
        localStorage.removeItem('token')
        setToken(null)
        setIsAuthenticated(false)
    }
    
    return (
        <Authcontext.Provider value={{token,isAuthenticated,login,logout}}>
            {children}
        </Authcontext.Provider>
    )
}

export const useAuth= () => useContext(Authcontext)