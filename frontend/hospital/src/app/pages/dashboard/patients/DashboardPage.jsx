import React, { useState, useEffect } from 'react'
import DashNavbar from '../../../components/layout/DashNavbar'
import { Sidebar } from '../../../components/layout/Sidebar'
import DashboardContent from './DashboardContent'


export const DashboardPage = () => {
  const [activeTab, setActiveTab] = useState('overview')

  useEffect(() => {
    // Fetch dashboard data
  }, [])


  const handleLinkClick = (tabId) => {
    console.log('Sidebar link clicked:', tabId) 
    setActiveTab(tabId) 
  }

  const dashboardLinks = [
    { label: 'Overview', tabId: 'overview' },
    { label: 'Reserve', tabId: 'reserve' },
    { label: 'Health', tabId: 'health' },
    { label: 'Advices', tabId: 'advices' },
    { label: 'Appointments', tabId: 'appointments' }
  ]

  return (
    <>
      <DashNavbar />
      <div className="flex w-full">
        <Sidebar
          links={dashboardLinks}
          activeTab={activeTab}
          onLinkClick={handleLinkClick}
        />
        <div className="flex-1 w-[80%]">
          <DashboardContent activeTab={activeTab} />
        </div>
      </div>
    </>
  )
}

export default DashboardPage
