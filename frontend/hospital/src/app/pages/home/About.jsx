import React from 'react'
import { Heart, Users, Award } from 'lucide-react'

export const About = () => {
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
    <div className="min-h-screen bg-gradient-to-b from-blue-50 to-white">
      {/* Hero Section */}
      <div className="bg-blue-600 text-white py-16">
        <div className="max-w-6xl mx-auto px-4">
          <h1 className="text-5xl font-bold mb-4">About Our Hospital</h1>
          <p className="text-xl text-blue-100">Delivering Excellence in Healthcare</p>
        </div>
      </div>

      {/* Mission Section */}
      <div className="max-w-6xl mx-auto px-4 py-16">
        <div className="grid md:grid-cols-2 gap-12 items-center">
          <div>
            <h2 className="text-4xl font-bold text-gray-800 mb-6">Our Mission</h2>
            <p className="text-gray-700 text-lg mb-4">
              We are committed to providing high-quality healthcare services to our community.
              Our mission is to improve the health and well-being of every patient we serve.
            </p>
            <p className="text-gray-700 text-lg">
              With state-of-the-art facilities and a compassionate team, we strive to be
              the trusted healthcare partner for our patients and their families.
            </p>
          </div>
          <div className="bg-blue-500 h-96 rounded-lg shadow-lg"></div>
        </div>
      </div>

      {/* Features */}
      <div className="bg-gray-50 py-16">
        <div className="max-w-6xl mx-auto px-4">
          <h2 className="text-4xl font-bold text-center text-gray-800 mb-12">Why Choose Us</h2>
          <div className="grid md:grid-cols-3 gap-8">
            {features.map((feature, index) => {
              const Icon = feature.icon
              return (
                <div key={index} className="bg-white p-8 rounded-lg shadow-md text-center">
                  <Icon className="w-16 h-16 text-blue-600 mx-auto mb-4" />
                  <h3 className="text-2xl font-bold text-gray-800 mb-3">{feature.title}</h3>
                  <p className="text-gray-600">{feature.description}</p>
                </div>
              )
            })}
          </div>
        </div>
      </div>

      {/* Stats */}
      <div className="bg-blue-600 text-white py-16">
        <div className="max-w-6xl mx-auto px-4">
          <div className="grid md:grid-cols-4 gap-8 text-center">
            <div>
              <div className="text-5xl font-bold mb-2">500+</div>
              <p className="text-blue-100">Doctors & Specialists</p>
            </div>
            <div>
              <div className="text-5xl font-bold mb-2">50K+</div>
              <p className="text-blue-100">Patients Served</p>
            </div>
            <div>
              <div className="text-5xl font-bold mb-2">25+</div>
              <p className="text-blue-100">Years Experience</p>
            </div>
            <div>
              <div className="text-5xl font-bold mb-2">100%</div>
              <p className="text-blue-100">Patient Satisfaction</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}
