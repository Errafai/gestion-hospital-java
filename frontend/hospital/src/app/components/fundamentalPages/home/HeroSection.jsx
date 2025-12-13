import React, { useEffect, useRef } from 'react'
import { ArrowRight, Phone } from 'lucide-react'
import * as THREE from 'three'

export const HeroSection = () => {
  const canvasRef = useRef(null)
  const sceneRef = useRef(null)

  useEffect(() => {
    if (!canvasRef.current) return

    // Scene setup
    const scene = new THREE.Scene()
    sceneRef.current = scene
    
    const camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 0.1, 1000)
    camera.position.z = 5

    const renderer = new THREE.WebGLRenderer({ 
      canvas: canvasRef.current, 
      alpha: true,
      antialias: true 
    })
    renderer.setSize(window.innerWidth, window.innerHeight)
    renderer.setPixelRatio(window.devicePixelRatio)

    // Lighting
    const ambientLight = new THREE.AmbientLight(0xffffff, 0.6)
    scene.add(ambientLight)

    const directionalLight = new THREE.DirectionalLight(0xffffff, 0.8)
    directionalLight.position.set(5, 5, 5)
    scene.add(directionalLight)

    const pointLight = new THREE.PointLight(0x06b6d4, 1, 100)
    pointLight.position.set(-5, 5, 5)
    scene.add(pointLight)

    // Create medical objects
    const objects = []

    // Function to create capsule/pill
    const createCapsule = (color1, color2) => {
      const group = new THREE.Group()
      
      // Cylinder body
      const bodyGeometry = new THREE.CylinderGeometry(0.15, 0.15, 0.5, 32)
      const bodyMaterial = new THREE.MeshPhongMaterial({ 
        color: color1,
        shininess: 100,
        specular: 0x444444
      })
      const body = new THREE.Mesh(bodyGeometry, bodyMaterial)
      group.add(body)

      // Top hemisphere
      const topGeometry = new THREE.SphereGeometry(0.15, 32, 16, 0, Math.PI * 2, 0, Math.PI / 2)
      const topMaterial = new THREE.MeshPhongMaterial({ 
        color: color1,
        shininess: 100,
        specular: 0x444444
      })
      const top = new THREE.Mesh(topGeometry, topMaterial)
      top.position.y = 0.25
      group.add(top)

      // Bottom hemisphere
      const bottomGeometry = new THREE.SphereGeometry(0.15, 32, 16, 0, Math.PI * 2, Math.PI / 2, Math.PI / 2)
      const bottomMaterial = new THREE.MeshPhongMaterial({ 
        color: color2,
        shininess: 100,
        specular: 0x444444
      })
      const bottom = new THREE.Mesh(bottomGeometry, bottomMaterial)
      bottom.position.y = -0.25
      group.add(bottom)

      return group
    }

    // Function to create vaccine vial
    const createVial = () => {
      const group = new THREE.Group()
      
      // Vial body
      const bodyGeometry = new THREE.CylinderGeometry(0.2, 0.2, 0.8, 32)
      const bodyMaterial = new THREE.MeshPhongMaterial({ 
        color: 0x06b6d4,
        transparent: true,
        opacity: 0.7,
        shininess: 100
      })
      const body = new THREE.Mesh(bodyGeometry, bodyMaterial)
      group.add(body)

      // Cap
      const capGeometry = new THREE.CylinderGeometry(0.22, 0.18, 0.15, 32)
      const capMaterial = new THREE.MeshPhongMaterial({ 
        color: 0xdc2626,
        shininess: 100
      })
      const cap = new THREE.Mesh(capGeometry, capMaterial)
      cap.position.y = 0.475
      group.add(cap)

      // Label
      const labelGeometry = new THREE.CylinderGeometry(0.21, 0.21, 0.3, 32)
      const labelMaterial = new THREE.MeshPhongMaterial({ 
        color: 0xffffff,
        shininess: 50
      })
      const label = new THREE.Mesh(labelGeometry, labelMaterial)
      label.position.y = 0
      group.add(label)

      return group
    }

    // Function to create COVID-19 virus
    const createVirus = () => {
      const group = new THREE.Group()
      
      // Main sphere body (gray)
      const bodyGeometry = new THREE.SphereGeometry(0.6, 32, 32)
      const bodyMaterial = new THREE.MeshPhongMaterial({ 
        color: 0x808080,
        shininess: 30,
        roughness: 0.8
      })
      const body = new THREE.Mesh(bodyGeometry, bodyMaterial)
      group.add(body)

      // Create spike proteins (red/orange protrusions)
      const spikeCount = 40
      for (let i = 0; i < spikeCount; i++) {
        // Random position on sphere surface
        const phi = Math.acos(-1 + (2 * i) / spikeCount)
        const theta = Math.sqrt(spikeCount * Math.PI) * phi
        
        const spike = new THREE.Group()
        
        // Spike stem (thinner cylinder)
        const stemGeometry = new THREE.CylinderGeometry(0.04, 0.06, 0.25, 8)
        const stemMaterial = new THREE.MeshPhongMaterial({ 
          color: 0xff6347,
          shininess: 50
        })
        const stem = new THREE.Mesh(stemGeometry, stemMaterial)
        stem.position.y = 0.125
        spike.add(stem)
        
        // Spike head (bulbous tip)
        const headGeometry = new THREE.SphereGeometry(0.08, 8, 8)
        const headMaterial = new THREE.MeshPhongMaterial({ 
          color: 0xdc143c,
          shininess: 80
        })
        const head = new THREE.Mesh(headGeometry, headMaterial)
        head.position.y = 0.25
        spike.add(head)
        
        // Position spike on sphere surface
        spike.position.x = 0.6 * Math.sin(phi) * Math.cos(theta)
        spike.position.y = 0.6 * Math.sin(phi) * Math.sin(theta)
        spike.position.z = 0.6 * Math.cos(phi)
        
        // Point spike outward
        spike.lookAt(
          spike.position.x * 2,
          spike.position.y * 2,
          spike.position.z * 2
        )
        
        group.add(spike)
      }

      // Add small orange/yellow dots on surface
      const dotCount = 20
      for (let i = 0; i < dotCount; i++) {
        const phi = Math.random() * Math.PI
        const theta = Math.random() * Math.PI * 2
        
        const dotGeometry = new THREE.SphereGeometry(0.05, 8, 8)
        const dotMaterial = new THREE.MeshPhongMaterial({ 
          color: Math.random() > 0.5 ? 0xff8c00 : 0xffd700,
          shininess: 100
        })
        const dot = new THREE.Mesh(dotGeometry, dotMaterial)
        
        dot.position.x = 0.62 * Math.sin(phi) * Math.cos(theta)
        dot.position.y = 0.62 * Math.sin(phi) * Math.sin(theta)
        dot.position.z = 0.62 * Math.cos(phi)
        
        group.add(dot)
      }

      return group
    }

    // Create multiple objects
    for (let i = 0; i < 12; i++) {
      let obj
      if (i % 4 === 0) {
        obj = createVirus()
        obj.scale.set(1.5, 1.5, 1.5) // Make virus bigger
      } else if (i % 4 === 1) {
        obj = createVial()
      } else if (i % 4 === 2) {
        obj = createCapsule(0x1e293b, 0xf1f5f9)
      } else {
        obj = createCapsule(0xef4444, 0xfbbf24)
      }

      // Random position
      obj.position.x = (Math.random() - 0.5) * 10
      obj.position.y = (Math.random() - 0.5) * 8
      obj.position.z = (Math.random() - 0.5) * 5

      // Random rotation speed (slower for virus)
      const isVirus = i % 4 === 0
      obj.userData = {
        rotationSpeed: {
          x: (Math.random() - 0.5) * (isVirus ? 0.01 : 0.02),
          y: (Math.random() - 0.5) * (isVirus ? 0.01 : 0.02),
          z: (Math.random() - 0.5) * (isVirus ? 0.01 : 0.02)
        },
        floatSpeed: Math.random() * 0.01 + (isVirus ? 0.003 : 0.005),
        floatOffset: Math.random() * Math.PI * 2
      }

      scene.add(obj)
      objects.push(obj)
    }

    // Animation
    let time = 0
    const animate = () => {
      requestAnimationFrame(animate)
      time += 0.01

      objects.forEach((obj) => {
        // Rotation
        obj.rotation.x += obj.userData.rotationSpeed.x
        obj.rotation.y += obj.userData.rotationSpeed.y
        obj.rotation.z += obj.userData.rotationSpeed.z

        // Floating motion
        obj.position.y += Math.sin(time * obj.userData.floatSpeed + obj.userData.floatOffset) * 0.002
        obj.position.x += Math.cos(time * obj.userData.floatSpeed * 0.7 + obj.userData.floatOffset) * 0.001
      })

      renderer.render(scene, camera)
    }
    animate()

    // Handle resize
    const handleResize = () => {
      camera.aspect = window.innerWidth / window.innerHeight
      camera.updateProjectionMatrix()
      renderer.setSize(window.innerWidth, window.innerHeight)
    }
    window.addEventListener('resize', handleResize)

    // Cleanup
    return () => {
      window.removeEventListener('resize', handleResize)
      renderer.dispose()
    }
  }, [])

  return (
    <section className='relative min-h-screen overflow-hidden bg-gradient-to-br from-cyan-700 via-blue-700 to-cyan-600'>
      {/* 3D Canvas Background */}
      <canvas 
        ref={canvasRef} 
        className='absolute inset-0 w-full h-full'
        style={{ opacity: 0.4 }}
      />

      {/* Gradient Overlay */}
      <div className='absolute inset-0 bg-gradient-to-r from-blue-900/80 to-transparent' />

      {/* Content */}
      <div className='relative max-w-7xl mx-auto px-6 py-20 grid md:grid-cols-2 gap-12 items-center min-h-screen'>
        <div className='text-white space-y-8 z-10'>
          <div>
            <span className='inline-block bg-white/20 backdrop-blur-md text-white px-4 py-2 rounded-full text-sm font-semibold mb-6 border border-white/30'>
              ✨ Excellence in Healthcare
            </span>
            <h1 className='text-5xl md:text-6xl font-bold leading-tight mb-4'>
              Welcome to <br />
              <span className='text-transparent bg-clip-text bg-gradient-to-r from-yellow-200 via-yellow-300 to-white'>
                Hospital ENSAS
              </span>
            </h1>
            <p className='text-xl text-blue-50 leading-relaxed max-w-lg'>
              Your health is our priority. We provide exceptional medical care with compassion, expertise, and cutting-edge technology.
            </p>
          </div>

          {/* Features List */}
          <div className='space-y-4'>
            {[
              'State-of-the-art medical facilities',
              'Expert healthcare professionals',
              'Personalized patient care'
            ].map((feature, i) => (
              <div key={i} className='flex items-center gap-3 text-blue-50'>
                <div className='w-3 h-3 bg-yellow-300 rounded-full shadow-lg shadow-yellow-300/50'></div>
                <span className='text-lg'>{feature}</span>
              </div>
            ))}
          </div>

          {/* CTA Buttons */}
          <div className='flex flex-wrap gap-4 pt-4'>
            <button 
              className='group bg-white text-blue-600 px-8 py-4 rounded-xl font-bold text-lg hover:bg-yellow-300 hover:text-blue-900 transition-all duration-300 shadow-2xl hover:shadow-yellow-300/50 hover:scale-105 flex items-center gap-2'
            >
              Book Appointment
              <ArrowRight className='w-5 h-5 group-hover:translate-x-1 transition-transform' />
            </button>
            <button 
              className='border-2 border-white/50 text-white px-8 py-4 rounded-xl font-bold text-lg hover:bg-white/10 backdrop-blur transition-all duration-300 hover:border-white hover:scale-105'
            >
              Learn More
            </button>
          </div>

          {/* Contact Info */}
          <div className='flex items-center gap-3 text-white pt-4 bg-white/10 backdrop-blur-md px-6 py-4 rounded-xl border border-white/20'>
            <div className='bg-yellow-300 p-2 rounded-lg'>
              <Phone className='w-5 h-5 text-blue-900' />
            </div>
            <div>
              <p className='text-sm text-blue-100'>24/7 Emergency</p>
              <span className='text-lg font-bold'>+1 (555) 123-4567</span>
            </div>
          </div>
        </div>

        {/* Right side - decorative stats or empty for mobile
        <div className='hidden md:flex flex-col gap-6 z-10'>
          <div className='bg-white/10 backdrop-blur-lg p-8 rounded-2xl border border-white/20 hover:bg-white/15 transition-all duration-300'>
            <h3 className='text-5xl font-bold text-yellow-300 mb-2'>10K+</h3>
            <p className='text-blue-50 text-lg'>Patients Treated</p>
          </div>
          <div className='bg-white/10 backdrop-blur-lg p-8 rounded-2xl border border-white/20 hover:bg-white/15 transition-all duration-300'>
            <h3 className='text-5xl font-bold text-yellow-300 mb-2'>50+</h3>
            <p className='text-blue-50 text-lg'>Expert Doctors</p>
          </div>
          <div className='bg-white/10 backdrop-blur-lg p-8 rounded-2xl border border-white/20 hover:bg-white/15 transition-all duration-300'>
            <h3 className='text-5xl font-bold text-yellow-300 mb-2'>99%</h3>
            <p className='text-blue-50 text-lg'>Satisfaction Rate</p>
          </div>
        </div> */}
      </div>
    </section>
  )
}

export default HeroSection