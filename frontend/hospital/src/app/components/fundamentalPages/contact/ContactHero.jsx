import React from 'react'
import contactUs from '../../../../assets/images/contact.jpg'
import { Users, Award, Zap, Phone } from "lucide-react";
export const ContactHero = () => {

  const styling = `
    .hero{
      background: linear-gradient(130deg, rgba(0, 0, 0, .4) 0%, rgba(0, 0, 0, .5) 100%), url(${contactUs}) center / cover no-repeat;
      background-attachment: fixed;
      position:relative;
      display:flex;
      align-items:center;
    }

    .hero::before{
      content:'';
      position:absolute;
      top-0;
      left:0;
      right:0;
      // background: linear-gradient(135deg, rgba(79, 70, 229, 0.4) 0%, rgba(59, 130, 246, 0.3) 100%);
      pointer-events:none;
    }

    hero > div {
      position: relative;
      z-index: 10;
    }
  `

  const stats = [
    { icon: Phone, label: "Patient Answering", value: "5K+ / Day" },
    { icon: Users, label: "Calls center", value: "500+" },
    { icon: Award, label: "Excellence", value: "15+" },
    { icon: Zap, label: "Innovation", value: "100%" }
  ];

  return (
    <div>
      <style>{styling}</style>
      <div className="hero w-full h-screen">
        <div className="max-w-6xl mx-auto px-4 md:px-6 w-full">
          <h1 className="text-5xl md:text-7xl font-bold mb-4 text-white drop-shadow-lg">
            Contact Us About Anything
          </h1>
          <p className="text-xl md:text-2xl text-blue-50 drop-shadow-md font-light mb-8 max-w-2xl">
            Innovation, Education, and Compassion at the Heart of Healthcare
          </p>
          <p className="text-lg text-blue-100 drop-shadow-md mb-12 max-w-3xl leading-relaxed">
            ENSA Hospital is a modern healthcare institution committed to delivering high-quality medical services while fostering innovation, research, and education. We combine medical expertise with technological advancement to improve patient care and healthcare systems.
          </p>
          
          <div className="grid grid-cols-2 md:grid-cols-4 gap-6 mb-8">
            {stats.map((stat, idx) => {
              const Icon = stat.icon;
              return (
                <div key={idx} className="border border-white bg-opacity-10 backdrop-blur-md rounded-lg p-6 text-center hover:bg-opacity-20 transition">
                  <Icon className="w-8 h-8 text-white mx-auto mb-3" />
                  <p className="text-3xl font-bold text-white mb-2">{stat.value}</p>
                  <p className="text-blue-100 text-sm">{stat.label}</p>
                </div>
              );
            })}
          </div>
        </div>
      </div>
    </div>
  )
}

export default ContactHero
