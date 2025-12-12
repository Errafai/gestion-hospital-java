import React from 'react'

export const MissionSection = () => {
  return (
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
        <div className="bg-gradient-to-br from-blue-500 to-blue-600 h-96 rounded-lg shadow-lg"></div>
      </div>
    </div>
  )
}

export default MissionSection
