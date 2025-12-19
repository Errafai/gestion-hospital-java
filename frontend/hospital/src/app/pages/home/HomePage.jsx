import { HeroSection, EquipmentSection, ServicesSection, CTABanner } from '../../components/fundamentalPages/home'
import Footer from '../../components/layout/Footer'
import Navbar from '../../components/layout/Navbar'

export default function HomePage() {
  return (
    <div className='w-full bg-white overflow-hidden'>
      <Navbar/>
      <HeroSection />
      <EquipmentSection />
      <ServicesSection />
      <CTABanner />
      <Footer/>
    </div>
  )
}
