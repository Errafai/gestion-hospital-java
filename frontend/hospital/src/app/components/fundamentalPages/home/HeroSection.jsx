import React from 'react'
import { useNavigate } from 'react-router-dom'
import { ArrowRight, Phone } from 'lucide-react'
import background from '../../../../assets/images/backround.jpg'

export const HeroSection = () => {
  const navigate = useNavigate()

  return (
    <section 
      className='relative min-h-screen bg-cover bg-center bg-no-repeat' 
      style={{
        backgroundImage: `linear-gradient(135deg, rgba(40, 90, 235, 0.55) 0%, rgba(6, 182, 212, 0.25) 100%), url(${background})`,
        backgroundSize: 'cover',
        backgroundPosition: 'center'  
      }}
    >
      <div className='relative max-w-7xl mx-auto px-6 py-20 grid md:grid-cols-2 gap-12 items-center min-h-screen'>
        <div className='text-white space-y-8 z-10'>
          <div>
            <span className='inline-block bg-white/20 backdrop-blur-md text-white px-4 py-2 rounded-full text-sm font-semibold mb-6'>
              ✨ Excellence in Healthcare
            </span>
            <h1 className='text-5xl md:text-6xl font-bold leading-tight mb-4'>
              Welcome to <br />
              <span className='text-transparent bg-clip-text bg-gradient-to-t from-yellow-200 to-white'>Hospital ENSAS</span>
            </h1>
            <p className='text-xl text-blue-50 leading-relaxed max-w-lg'>
              Your health is our priority. We provide exceptional medical care with compassion, expertise, and cutting-edge technology.
            </p>
          </div>

          {/* Features List */}
          <div className='space-y-4'>
            {[
              'State-of-the-art medical facilities',
              'Expert healthcare professionals',
              'Personalized patient care'
            ].map((feature, i) => (
              <div key={i} className='flex items-center gap-3 text-blue-50'>
                <div className='w-3 h-3 bg-yellow-300 rounded-full'></div>
                <span>{feature}</span>
              </div>
            ))}
          </div>

          {/* CTA Buttons */}
          <div className='flex flex-wrap gap-4 pt-4'>
            <button 
              onClick={() => navigate('/appointments')} 
              className='group bg-white text-blue-600 px-8 py-3 rounded-lg font-bold text-lg hover:bg-yellow-300 transition-all duration-300 shadow-lg hover:shadow-2xl flex items-center gap-2'
            >
              Book Appointment
              <ArrowRight className='w-5 h-5 group-hover:translate-x-1 transition-transform' />
            </button>
            <button 
              onClick={() => navigate('/about-us')} 
              className='border-2 border-white text-white px-8 py-3 rounded-lg font-bold text-lg hover:bg-white/10 backdrop-blur transition-all duration-300'
            >
              Learn More
            </button>
          </div>

          {/* Contact Info */}
          <div className='flex items-center gap-3 text-white pt-4'>
            <Phone className='w-5 h-5' />
            <span className='text-lg font-semibold'>Emergency: +1 (555) 123-4567</span>
          </div>
        </div>
      </div>
    </section>
  )
}

export default HeroSection
