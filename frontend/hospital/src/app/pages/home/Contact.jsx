import React from 'react'
import { ContactHero, ContactInfo, ContactForm } from '../../components/fundamentalPages/contact'
import { Clock, MessageSquare, CheckCircle } from 'lucide-react'

export const Contact = () => {
  const benefits = [
    { icon: Clock, title: "Quick Response", desc: "Get answers within 24 hours" },
    { icon: MessageSquare, title: "Multiple Channels", desc: "Reach us via phone, email, or chat" },
    { icon: CheckCircle, title: "Expert Support", desc: "Speak with healthcare professionals" }
  ]

  return (
    <div className="min-h-screen bg-gradient-to-b from-blue-50 to-white">
      <ContactHero />
      
      {/* Benefits Section */}
      <div className="bg-gradient-to-r from-blue-600 to-blue-800 py-16 px-4">
        <div className="max-w-6xl mx-auto">
          <h2 className="text-4xl font-bold text-white mb-12 text-center">Why Contact Us?</h2>
          <div className="grid md:grid-cols-3 gap-8">
            {benefits.map((benefit, idx) => {
              const Icon = benefit.icon
              return (
                <div key={idx} className="border border-white bg-opacity-10 backdrop-blur-md rounded-lg p-8 text-white text-center hover:bg-opacity-20 transition">
                  <Icon className="w-12 h-12 mx-auto mb-4" />
                  <h3 className="text-xl font-semibold mb-2">{benefit.title}</h3>
                  <p className="text-blue-100">{benefit.desc}</p>
                </div>
              )
            })}
          </div>
        </div>
      </div>

      {/* Main Contact Section */}
      <div className="py-20 px-4">
        <div className="max-w-6xl mx-auto mb-12">
          <h2 className="text-4xl font-bold text-gray-800 mb-4">We'd Love to Hear From You</h2>
          <p className="text-lg text-gray-600 mb-8 max-w-2xl">
            Whether you have a question about our services, need medical advice, or want to schedule an appointment, our team is ready to assist you. Fill out the form below or use any of our contact methods.
          </p>
        </div>

        <div className="flex flex-col lg:flex-row gap-12 max-w-6xl mx-auto">
          <div className="lg:flex-1">
            <ContactInfo />
          </div>
          <div className="lg:flex-1">
            <ContactForm />
          </div>
        </div>
      </div>

      {/* Additional Info Section */}
      <div className="bg-gray-50 py-16 px-4">
        <div className="max-w-4xl mx-auto">
          <h3 className="text-3xl font-bold text-gray-800 mb-8">Frequently Asked Questions</h3>
          <div className="space-y-6">
            <div className="bg-white p-6 rounded-lg shadow-md border-l-4 border-blue-600">
              <h4 className="text-lg font-semibold text-gray-800 mb-2">How do I schedule an appointment?</h4>
              <p className="text-gray-600">You can schedule an appointment by calling our hotline, using our online form, or visiting our clinic directly. Most appointments can be scheduled within 48 hours.</p>
            </div>
            <div className="bg-white p-6 rounded-lg shadow-md border-l-4 border-blue-600">
              <h4 className="text-lg font-semibold text-gray-800 mb-2">What are your emergency services available?</h4>
              <p className="text-gray-600">ENSA Hospital offers 24/7 emergency services. In case of a medical emergency, please call our emergency hotline immediately or visit the emergency department.</p>
            </div>
            <div className="bg-white p-6 rounded-lg shadow-md border-l-4 border-blue-600">
              <h4 className="text-lg font-semibold text-gray-800 mb-2">Do you offer online consultations?</h4>
              <p className="text-gray-600">Yes, we offer virtual consultations for certain services. Contact our team to determine if your healthcare needs can be addressed through online consultation.</p>
            </div>
          </div>
        </div>
      </div>

      {/* CTA Section */}
      <div className="bg-gradient-to-r from-blue-600 to-blue-800 py-16 px-4">
        <div className="max-w-3xl mx-auto text-center text-white">
          <h3 className="text-3xl font-bold mb-4">Still Have Questions?</h3>
          <p className="text-lg text-blue-100 mb-8">
            Don't hesitate to reach out. Our dedicated team is always ready to help you with any concerns or inquiries.
          </p>
          <button className="px-8 py-3 bg-white text-blue-600 font-semibold rounded-lg hover:bg-blue-50 transition">
            Get in Touch Now
          </button>
        </div>
      </div>
    </div>
  )
}

export default Contact
