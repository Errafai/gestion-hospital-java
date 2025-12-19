import React from 'react'
import { AboutHero, MissionSection, WhyChooseUs, AboutStats } from '../../components/fundamentalPages/about'
import Navbar from '../../components/layout/Navbar'
import Footer from '../../components/layout/Footer'

export const About = () => {
  return (
    <div className="min-h-screen bg-gradient-to-b from-blue-50 to-white">
      <Navbar/>
      <AboutHero />
      <MissionSection />
      <WhyChooseUs />
      <AboutStats />
      <Footer />

    </div>
  )
}

export default About
