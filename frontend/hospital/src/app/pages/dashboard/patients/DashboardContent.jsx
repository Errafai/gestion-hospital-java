import React from 'react'
import OverviewPage from './OverviewPage'
import AppointementsPage from './AppointementsPage'
import AnanlysisPage from './AnanlysisPage'
import Reserve from './Reserve'

export const DashboardContent = ({ activeTab = 'overview' }) => {
    return (
        <div className="p-6">
            {activeTab === 'overview' && <OverviewPage />}
            {activeTab === 'appointments' && <AppointementsPage />}
            {activeTab === 'analysis' && <AnanlysisPage />}
            {activeTab === 'reserve' && <Reserve/>}
            {activeTab === 'health' && <div><h2>Health Page - Coming Soon</h2></div>}
            {activeTab === 'advices' && <div><h2>Advices Page - Coming Soon</h2></div>}
        </div>
    )
}

export default DashboardContent