import React from 'react'
import { EquipmentHero, EquipmentGrid, QualityAssurance } from '../../components/fundamentalPages/equipment'
import Navbar from '../../components/layout/Navbar'
import Footer from '../../components/layout/Footer'

export const Equipment = () => {
  return (
    <div className="min-h-screen bg-gradient-to-b from-blue-50 to-white">
      <Navbar/>
      <EquipmentHero />
      <EquipmentGrid />
      <QualityAssurance />
      <Footer/>
    </div>
  )
}

export default Equipment