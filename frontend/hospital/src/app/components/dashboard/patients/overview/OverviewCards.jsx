import React from 'react'

export const OverviewCards = ({ overviewData }) => {

    const handelColor = (id) => {
        switch (id) {
            case 'total-appointments':
                return 'bg-green-500';
            case 'total-asking':
                return 'bg-yellow-500';
            case 'last-appointment':
                return 'bg-orange-500';
            case 'next-appointment':
                return 'bg-purple-500';
            default:
                return 'bg-gray-500';
        }
    }
    return (
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4 shadow-lg p-4 rounded-lg">
            {overviewData.map((items, idx) => {
                return (
                    <div key={idx} className={`${handelColor(items.id)} rounded-lg shadow-lg p-4 text-white h-40 mt-4`}>
                        <h3 className="text-lg font-bold mb-2">{items.label}</h3>
                        <p className="text-2xl font-bold">{items.value}</p>
                    </div>
                )
            })}
        </div>
    )
}

export default OverviewCards