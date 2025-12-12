import materiel1 from '../../../../assets/images/mater.jpg'
import massage from '../../../../assets/images/mater2.jpg'
import scanner from '../../../../assets/images/mater3.jpg'
import materielGlobal from '../../../../assets/images/mater4.jpg'

export const EquipmentSection = () => {
  const materiel = [
    { image: materiel1, title: "Advanced MRI Scanner", desc: "State-of-the-art imaging technology for accurate diagnostics" },
    { image: massage, title: "Robotic Surgical System", desc: "Precision surgery with minimal invasiveness" },
    { image: scanner, title: "Digital X-Ray Machine", desc: "High-resolution imaging with reduced radiation exposure" },
    { image: materielGlobal, title: "Patient Monitoring System", desc: "Real-time vital signs tracking for critical care" },
  ]

  return (
    <section className='bg-gradient-to-r from-blue-50 to-cyan-50 py-16 flex justify-center'>
      <div className='w-[90%] px-6'>
        <h1 className="text-4xl font-bold mb-4 text-gray-900">The Difference Equipments</h1>
        <p className='text-lg text-gray-600 mb-12 max-w-2xl'>
          Advanced medical technology that makes the difference in patient care
        </p>
        
        <div className='grid md:grid-cols-2 lg:grid-cols-4 gap-8'>
          {materiel.map((item, idx) => (
            <div 
              key={idx} 
              className='group bg-white rounded-xl overflow-hidden shadow-lg hover:shadow-2xl transition-all duration-300 hover:translate-y-[-4px]'
            >
              {/* Image Container */}
              <div className='relative h-68 bg-gradient-to-br from-blue-100 to-cyan-100 overflow-hidden'>
                <img 
                  src={item.image} 
                  alt={item.title} 
                  className='w-full h-full object-cover group-hover:scale-110 transition-transform duration-300'
                />
              </div>

              {/* Content */}
              <div className='p-6'>
                <h3 className='text-lg font-bold text-gray-900 mb-3'>{item.title}</h3>
                <p className='text-gray-600 text-sm leading-relaxed'>{item.desc}</p>
              </div>
            </div>
          ))}
        </div>
      </div>
    </section>
  )
}

export default EquipmentSection
