import React from 'react'
import { Activity, Zap, Beaker, Heart } from 'lucide-react'

export const Equipment = () => {
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
    <div className="min-h-screen bg-gradient-to-b from-blue-50 to-white">
      {/* Hero Section */}
      <div className="bg-blue-600 text-white py-16">
        <div className="max-w-6xl mx-auto px-4">
          <h1 className="text-5xl font-bold mb-4">Medical Equipment</h1>
          <p className="text-xl text-blue-100">State-of-the-Art Technology for Your Care</p>
        </div>
      </div>

      {/* Equipment Overview */}
      <div className="max-w-6xl mx-auto px-4 py-16">
        <div className="text-center mb-12">
          <h2 className="text-4xl font-bold text-gray-800 mb-4">Our Facilities</h2>
          <p className="text-xl text-gray-600 max-w-3xl mx-auto">
            We invest in the latest medical technology to ensure accurate diagnoses,
            effective treatments, and the best outcomes for our patients.
          </p>
        </div>

        {/* Equipment Grid */}
        <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-8">
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

      {/* Quality Assurance */}
      <div className="bg-gray-50 py-16">
        <div className="max-w-6xl mx-auto px-4">
          <h2 className="text-4xl font-bold text-center text-gray-800 mb-12">Quality Assurance</h2>
          <div className="grid md:grid-cols-3 gap-8">
            <div className="bg-white p-8 rounded-lg shadow">
              <h3 className="text-xl font-bold text-gray-800 mb-3">Regular Maintenance</h3>
              <p className="text-gray-600">All equipment is regularly serviced and maintained to ensure optimal performance</p>
            </div>
            <div className="bg-white p-8 rounded-lg shadow">
              <h3 className="text-xl font-bold text-gray-800 mb-3">Latest Technology</h3>
              <p className="text-gray-600">We continuously upgrade to incorporate the latest advancements in medical technology</p>
            </div>
            <div className="bg-white p-8 rounded-lg shadow">
              <h3 className="text-xl font-bold text-gray-800 mb-3">Safety Standards</h3>
              <p className="text-gray-600">All equipment meets international safety and quality standards</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}