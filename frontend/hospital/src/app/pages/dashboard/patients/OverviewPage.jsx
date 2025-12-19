import React from 'react'
import OverviewCards from '../../../components/dashboard/patients/overview/OverviewCards'
export const OverviewPage = () => {


  // at this point the backend teem will add the this information from database

  const overviewData = [
    { id: 'total-appointments', label: 'Total Appointments', value: 100 },
    { id: 'total-asking', label: 'Total Asking', value: 100 },
    { id: 'last-appointment', label: 'Last Appointment', value: '2025-01-01' },
    { id: 'next-appointment', label: 'Next Appointment', value: '2025-01-01' },
  ]

  return (
    <div>
      <h1 className="text-3xl md:text-5xl font-bold text-blue-500 drop-shadow-lg mb-4 font-bold">Global Overview</h1>
      <OverviewCards overviewData={overviewData} />
    </div>
  )
}

export default OverviewPage