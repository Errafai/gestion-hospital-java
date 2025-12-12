import React from 'react'
import { useNavigate } from 'react-router-dom'

export const CTABanner = () => {
  const navigate = useNavigate()

  return (
    <section className='bg-gradient-to-r from-blue-600 to-cyan-600 py-16'>
      <div className='max-w-4xl mx-auto px-6 text-center text-white'>
        <h2 className='text-4xl font-bold mb-6'>Ready to Prioritize Your Health?</h2>
        <p className='text-xl text-blue-100 mb-8 max-w-2xl mx-auto'>
          Schedule your appointment today and experience exceptional healthcare with our dedicated team.
        </p>
        <div className='flex flex-wrap gap-4 justify-center'>
          <button 
            onClick={() => navigate('/appointments')}
            className='bg-white text-blue-600 px-8 py-3 rounded-lg font-bold hover:bg-blue-50 transition-all shadow-lg hover:shadow-xl'
          >
            Book Now
          </button>
          <button 
            onClick={() => navigate('/contact-us')}
            className='border-2 border-white text-white px-8 py-3 rounded-lg font-bold hover:bg-white hover:text-blue-600 transition-all'
          >
            Contact Us
          </button>
        </div>
      </div>
    </section>
  )
}

export default CTABanner
