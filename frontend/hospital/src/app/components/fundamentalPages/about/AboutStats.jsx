import React from 'react'

export const AboutStats = () => {
  const stats = [
    { number: '500+', label: 'Doctors & Specialists' },
    { number: '50K+', label: 'Patients Served' },
    { number: '25+', label: 'Years Experience' },
    { number: '100%', label: 'Patient Satisfaction' }
  ]

  return (
    <div className="bg-blue-400 text-white py-16">
      <div className="max-w-6xl mx-auto px-4">
        <div className="grid md:grid-cols-4 gap-8 text-center">
          {stats.map((stat, idx) => (
            <div key={idx}>
              <div className="text-5xl font-bold mb-2">{stat.number}</div>
              <p className="text-blue-100">{stat.label}</p>
            </div>
          ))}
        </div>
      </div>
    </div>
  )
}

export default AboutStats
