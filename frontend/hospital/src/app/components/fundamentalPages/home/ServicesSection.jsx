import React from 'react'
import { Heart, Clock, Users, Shield } from 'lucide-react'

export const ServicesSection = () => {
  const stats = [
    { number: '500+', label: 'Expert Doctors', icon: Users },
    { number: '50K+', label: 'Patients Served', icon: Heart },
    { number: '24/7', label: 'Emergency Care', icon: Clock },
    { number: '100%', label: 'Patient Trust', icon: Shield }
  ]

  const services = [
    { icon: Heart, title: 'Emergency Care', desc: 'Immediate response team available 24/7' },
    { icon: Users, title: 'Specialist Doctors', desc: 'Highly qualified medical professionals' },
    { icon: Clock, title: 'Quick Service', desc: 'Minimal wait times and efficient care' },
    { icon: Shield, title: 'Safe & Secure', desc: 'Advanced safety protocols in place' }
  ]

  return (
    <>
      {/* Stats Section */}
      <section className='bg-gradient-to-r from-blue-50 to-cyan-50 py-16'>
        <div className='max-w-7xl mx-auto px-6'>
          <div className='text-center mb-16'>
            <h2 className='text-4xl font-bold text-gray-900 mb-4'>Why Hospital ENSAS</h2>
            <p className='text-lg text-gray-600 max-w-2xl mx-auto'>
              Our commitment to excellence in numbers
            </p>
          </div>

          <div className='grid md:grid-cols-4 gap-8'>
            {stats.map((stat, idx) => {
              const Icon = stat.icon
              return (
                <div key={idx} className='text-center bg-white rounded-2xl p-8 shadow-lg hover:shadow-xl transition-all'>
                  <div className='bg-blue-600 w-16 h-16 rounded-full flex items-center justify-center mx-auto mb-4 shadow-lg'>
                    <Icon className='w-8 h-8 text-white' />
                  </div>
                  <div className='text-4xl font-bold text-blue-600 mb-2'>{stat.number}</div>
                  <p className='text-gray-700 font-semibold'>{stat.label}</p>
                </div>
              )
            })}
          </div>
        </div>
      </section>

      {/* Services Section */}
      <section className='py-20 bg-white'>
        <div className='max-w-7xl mx-auto px-6'>
          <div className='text-center mb-16'>
            <h2 className='text-4xl md:text-5xl font-bold text-gray-900 mb-4'>Our Services</h2>
            <p className='text-xl text-gray-600 max-w-2xl mx-auto'>
              Comprehensive healthcare solutions tailored to your needs
            </p>
          </div>

          <div className='grid md:grid-cols-2 lg:grid-cols-4 gap-6'>
            {services.map((service, idx) => {
              const Icon = service.icon
              return (
                <div 
                  key={idx} 
                  className='group bg-gradient-to-br from-blue-50 to-cyan-50 p-8 rounded-2xl border border-blue-100 hover:border-blue-300 hover:shadow-xl transition-all duration-300 hover:translate-y-[-4px]'
                >
                  <div className='bg-blue-600 w-12 h-12 rounded-full flex items-center justify-center mb-4 group-hover:bg-blue-700 transition-colors'>
                    <Icon className='w-6 h-6 text-white' />
                  </div>
                  <h3 className='text-xl font-bold text-gray-900 mb-2'>{service.title}</h3>
                  <p className='text-gray-600'>{service.desc}</p>
                </div>
              )
            })}
          </div>
        </div>
      </section>
    </>
  )
}

export default ServicesSection
