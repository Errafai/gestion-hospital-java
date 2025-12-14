import React from 'react'
import { Heart, Clock, Users, Shield } from 'lucide-react'
import bigSpace from '../../../../assets/images/bigSpace.jpg'
import doctors from '../../../../assets/images/doctors.jpg'
import emergency1 from '../../../../assets/images/emergency1.jpg'
import emergency2 from '../../../../assets/images/emergency2.jpg'

const scrollingStyles = `
  @keyframes horizontalScroll {
    0% {
      transform: translateX(0);
    }
    100% {
      transform: translateX(-50%);
    }
  }
  
  .scroll-container {
    animation: horizontalScroll 20s linear infinite;
  }
  
  .scroll-container:hover {
    animation-play-state: paused;
  }
  
  .image-card {
    position: relative;
    overflow: hidden;
  }
  
  .image-card .desc-overlay {
    position: absolute;
    bottom: 10;
    left: 0;
    right: 0;
    background: rgba(100, 100, 100, 0.45);
    color: white;
    padding: 20px;
    text-align: center;
    font-weight: bold;
    font-size: 16px;
    opacity: 0;
    transition: opacity 0.3s ease-in-out;
  }
    
  .image-card:hover .desc-overlay {
    opacity: 1;
  }
`

export const ServicesSection = () => {

  const stats = [
    { number: '500+', label: 'Expert Doctors', icon: Users },
    { number: '50K+', label: 'Patients Served', icon: Heart },
    { number: '24/7', label: 'Emergency Care', icon: Clock },
    { number: '100%', label: 'Patient Trust', icon: Shield }
  ]

  const services1 = [
    { image: bigSpace, desc: 'Expert Doctors - Our team comprises highly qualified medical professionals with extensive experience in their respective specialties. Each doctor is board-certified and committed to providing the highest standard of patient care with compassion and expertise.' },
    { image: doctors, desc: 'Patients Served - Over 50,000 satisfied patients have entrusted us with their healthcare needs. Our track record of successful treatments and positive patient outcomes reflects our dedication to excellence and personalized care for every individual.' },
    { image: emergency1, desc: 'Emergency Care - Available 24/7, our state-of-the-art emergency department is equipped to handle urgent medical situations with rapid response times and advanced life support technology. Our emergency team is trained to manage critical cases effectively and efficiently.' },
    { image: emergency2, desc: 'Patient Trust - Built on years of reliability and medical excellence, patient trust is the cornerstone of our hospital. We maintain the highest standards of confidentiality, safety, and ethical practice to ensure your complete confidence in our care.' }
  ]

  const services = [
    { icon: Heart, title: 'Emergency Care ', desc: 'Immediate response team available 24/7' },
    { icon: Users, title: 'Specialist Doctors', desc: 'Highly qualified medical professionals' },
    { icon: Clock, title: 'Quick Service', desc: 'Minimal wait times and efficient care' },
    { icon: Shield, title: 'Safe & Secure', desc: 'Advanced safety protocols in place' }
  ]

  return (
    <>
      <style>{scrollingStyles}</style>
      {/* services1 Section */}
      <section className='w-full bg-gradient-to-r from-blue-50 to-cyan-50 py-16 flex justify-center overflow-hidden'>
        <div className='w-[90%] px-6'>
          <div className='max-w-2xl text-start mb-16'>
            <h2 className='text-4xl font-bold text-gray-900 mb-4'>Why Hospital ENSAS</h2>
            <p className='text-lg text-gray-600 max-w-2xl mx-auto'>
              Our commitment to excellence in numbers
            </p>
          </div>

          <div className='scroll-container flex gap-8' style={{ width: 'fit-content' }}>
            {[...services1, ...services1].map((service, idx) => {
              return (
                <div 
                  key={idx} 
                  className='image-card flex-shrink-0 rounded-2xl shadow-lg hover:shadow-xl transition-shadow' 
                  style={{
                    backgroundImage: `url(${service.image})`, 
                    backgroundSize: 'cover', 
                    backgroundPosition: 'center', 
                    height: '400px', 
                    width: '350px',
                  }}
                >
                  <div className="desc-overlay">{service.desc}</div>
                </div>
              )
            })}
          </div>
        </div>
      </section>

      {/* Services Section */}
      <section className='py-20 bg-white'>
        <div className=' w-[90%] mx-auto px-6'>
          <div className='max-w-2xl mb-16'>
            <h2 className='text-4xl md:text-5xl font-bold text-gray-900 mb-4'>Our Services</h2>
            <p className='text-xl text-gray-600 max-w-2xl mx-auto'>
              Comprehensive healthcare solutions tailored to your needs
            </p>
          </div>

          <div className='grid md:grid-cols-1 lg:grid-cols-2 gap-6'>
            {services.map((service, idx) => {
              const Icon = service.icon
              return (
                <div 
                  key={idx} 
                  className=' group bg-gradient-to-br from-blue-50 to-cyan-50 p-8 rounded-2xl border border-blue-100 hover:border-blue-300 hover:shadow-xl transition-all duration-300 hover:translate-y-[-6px]'
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
