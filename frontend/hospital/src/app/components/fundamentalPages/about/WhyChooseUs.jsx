import React from 'react'
import { Heart, Users, Award } from 'lucide-react'

export const WhyChooseUs = () => {
  const features = [
    {
      icon: Heart,
      title: 'Patient Care',
      description: 'We prioritize the health and well-being of our patients with compassionate care.'
    },
    {
      icon: Users,
      title: 'Expert Team',
      description: 'Our team of experienced medical professionals is dedicated to excellence.'
    },
    {
      icon: Award,
      title: 'Accredited',
      description: 'Certified and accredited by international health organizations.'
    }
  ]

  return (
    <div className="bg-gray-50 py-16">
      <div className="max-w-6xl mx-auto px-4">
        <h2 className="text-4xl font-bold text-center text-gray-800 mb-12">Why Choose Us</h2>
        <div className="grid md:grid-cols-3 gap-8">
          {features.map((feature, index) => {
            const Icon = feature.icon
            return (
              <div key={index} className="bg-white p-8 rounded-lg shadow-md text-center hover:shadow-lg transition-shadow">
                <Icon className="w-16 h-16 text-blue-600 mx-auto mb-4" />
                <h3 className="text-2xl font-bold text-gray-800 mb-3">{feature.title}</h3>
                <p className="text-gray-600">{feature.description}</p>
              </div>
            )
          })}
        </div>
      </div>
    </div>
  )
}

export default WhyChooseUs
