import React, { useContext } from 'react'
import { NavContext } from './NavProvider'

export default function EditFile() {
    const {setCurrentPath} = useContext(NavContext)
    setCurrentPath("/edit")

    return (
        <div>
            
        </div>
    )
}
