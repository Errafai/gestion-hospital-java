import { HeroSection, EquipmentSection, ServicesSection, CTABanner } from '../../components/fundamentalPages/home'

export default function HomePage() {
  return (
    <div className='w-full bg-white overflow-hidden'>
      <HeroSection />
      <EquipmentSection />
      <ServicesSection />
      <CTABanner />
    </div>
  )
}
