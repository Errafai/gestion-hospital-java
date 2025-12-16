import React from 'react'
import { Activity, Zap, Beaker, Heart } from 'lucide-react'

export const EquipmentGrid = () => {
  const equipmentList = [
    {
      icon: Heart,
      name: 'Cardiac Monitor',
      description: 'Advanced cardiac monitoring systems for real-time patient heart rate and rhythm tracking',
      specs: ['24/7 Monitoring', 'Wireless Technology', 'Alert System']
    },
    {
      icon: Activity,
      name: 'ECG Machine',
      description: 'State-of-the-art electrocardiogram machines for heart activity diagnosis',
      specs: ['12-Lead ECG', 'Digital Recording', 'Instant Reporting']
    },
    {
      icon: Zap,
      name: 'Ultrasound System',
      description: 'High-resolution ultrasound imaging for accurate diagnostic procedures',
      specs: ['4D Imaging', 'Multi-Probe Support', 'Real-time Analysis']
    },
    {
      icon: Beaker,
      name: 'Laboratory Analyzer',
      description: 'Automated laboratory equipment for precise blood and tissue analysis',
      specs: ['Multi-Parameter Testing', 'High Accuracy', 'Quick Results']
    },
    {
      icon: Activity,
      name: 'X-Ray Machine',
      description: 'Digital radiography system with minimal radiation exposure',
      specs: ['Low Dose', 'High Resolution', 'Digital Storage']
    },
    {
      icon: Heart,
      name: 'Ventilator',
      description: 'Advanced respiratory support systems for critical care patients',
      specs: ['Multiple Modes', 'ICU Grade', 'Safety Features']
    }
  ]

  return (
    <div className="w-[90%] mx-auto px-4 py-16">
      <div className="flex flex-col items-start mb-12">
        <h2 className="text-4xl font-bold text-gray-800 mb-4">Our Facilities</h2>
        <p className="text-xl text-gray-600 max-w-3xl ">
          We invest in the latest medical technology to ensure accurate diagnoses,
          effective treatments, and the best outcomes for our patients.
        </p>
      </div>

      {/* Equipment Grid */}
      <div className="grid md:grid-cols-1 lg:grid-cols-2 gap-8">
        {equipmentList.map((equipment, index) => {
          const Icon = equipment.icon
          return (
            <div key={index} className="bg-white rounded-lg shadow-lg overflow-hidden hover:shadow-xl transition-shadow">
              <div className="bg-gradient-to-r from-blue-500 to-blue-600 p-6 text-white">
                <Icon className="w-12 h-12 mb-3" />
                <h3 className="text-2xl font-bold">{equipment.name}</h3>
              </div>
              <div className="p-6">
                <p className="text-gray-700 mb-4">{equipment.description}</p>
                <h4 className="font-semibold text-gray-800 mb-3">Key Features:</h4>
                <ul className="space-y-2">
                  {equipment.specs.map((spec, i) => (
                    <li key={i} className="flex items-center gap-2 text-gray-600">
                      <span className="w-2 h-2 bg-blue-600 rounded-full"></span>
                      {spec}
                    </li>
                  ))}
                </ul>
              </div>
            </div>
          )
        })}
      </div>
    </div>
  )
}

export default EquipmentGrid
