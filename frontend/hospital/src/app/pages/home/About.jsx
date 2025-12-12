import React from 'react'
import { AboutHero, MissionSection, WhyChooseUs, AboutStats } from '../../components/fundamentalPages/about'

export const About = () => {
  return (
    <div className="min-h-screen bg-gradient-to-b from-blue-50 to-white">
      <AboutHero />
      <MissionSection />
      <WhyChooseUs />
      <AboutStats />
    </div>
  )
}

export default About
