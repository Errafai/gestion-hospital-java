import React from 'react'
import { ContactHero, ContactInfo, ContactForm } from '../../components/fundamentalPages/contact'

export const Contact = () => {
  return (
    <div className="min-h-screen bg-gradient-to-b from-blue-50 to-white">
      <ContactHero />
      <ContactInfo />
      <ContactForm />
    </div>
  )
}

export default Contact
