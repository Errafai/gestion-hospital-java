import React from 'react'

export const QualityAssurance = () => {
  const quality = [
    {
      title: 'Regular Maintenance',
      desc: 'All equipment is regularly serviced and maintained to ensure optimal performance'
    },
    {
      title: 'Latest Technology',
      desc: 'We continuously upgrade to incorporate the latest advancements in medical technology'
    },
    {
      title: 'Safety Standards',
      desc: 'All equipment meets international safety and quality standards'
    }
  ]

  return (
    <div className="bg-gray-50 py-16">
      <div className="max-w-6xl mx-auto px-4">
        <h2 className="text-4xl font-bold text-center text-gray-800 mb-12">Quality Assurance</h2>
        <div className="grid md:grid-cols-3 gap-8">
          {quality.map((item, idx) => (
            <div key={idx} className="bg-white p-8 rounded-lg shadow hover:shadow-lg transition-shadow">
              <h3 className="text-xl font-bold text-gray-800 mb-3">{item.title}</h3>
              <p className="text-gray-600">{item.desc}</p>
            </div>
          ))}
        </div>
      </div>
    </div>
  )
}

export default QualityAssurance
