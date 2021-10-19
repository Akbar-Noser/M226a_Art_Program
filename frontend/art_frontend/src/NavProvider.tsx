import React, { createContext, FC, useState } from 'react'
import Navbar from './Navbar'


type NavType = {
    currentPath: string,
    setCurrentPath: (newPath: string) => void
}

const initialNavValues = {
    currentPath: "/create",
    setCurrentPath: () => {}
}

export const NavContext = createContext<NavType>(initialNavValues)

const NavProvider : FC = ({children}) => {
    const [currentPath, setCurrentPath] = useState(initialNavValues.currentPath)
   
    

    return (
        <NavContext.Provider value={{currentPath, setCurrentPath}}>
            <Navbar/>
            {children}
        </NavContext.Provider>
        
    )
}

export default NavProvider;