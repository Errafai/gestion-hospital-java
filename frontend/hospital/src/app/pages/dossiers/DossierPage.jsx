import React, { useState, useEffect } from 'react'

export const DossierPage = () => {
  const [dossiers, setDossiers] = useState([])

  useEffect(() => {
    // Fetch dossiers
  }, [])

  return (
    <div className="p-6">
      <h1 className="text-3xl font-bold">Medical Dossiers</h1>
      {/* Dossiers list */}
    </div>
  )
}

export default DossierPage
