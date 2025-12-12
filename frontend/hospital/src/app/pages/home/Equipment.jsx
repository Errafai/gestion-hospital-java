import React from 'react'
import { EquipmentHero, EquipmentGrid, QualityAssurance } from '../../components/fundamentalPages/equipment'

export const Equipment = () => {
  return (
    <div className="min-h-screen bg-gradient-to-b from-blue-50 to-white">
      <EquipmentHero />
      <EquipmentGrid />
      <QualityAssurance />
    </div>
  )
}

export default Equipment