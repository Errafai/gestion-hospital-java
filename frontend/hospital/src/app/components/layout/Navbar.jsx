import { NavLink, useNavigate } from 'react-router-dom'
import { LogIn, UserPlus } from 'lucide-react'
import { navLinks, authLinks } from '../../routes/map'
import logo from '../../../assets/images/hospital-building.png'

export const Navbar = () => {
  const navigate = useNavigate()

  const buttonBase =
    'group relative overflow-hidden rounded-xl focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-white/70'

  return (
    <div className="w-full h-20 bg-blue-500 flex items-center px-4 shadow-md justify-between">
      <NavLink to="/" className="text-2xl font-bold text-white font-serif flex items-center justify-center text-4xl" ><img className='pl-7 py-2 pr-2'  style={{ height: '80px', width: 'auto' }} src={logo} alt="Hospital Logo" /> Hospital ENSAS</NavLink>
      <nav className="flex items-center gap-6">
        <ul className="flex items-center gap-6">
          {navLinks.map(({ label, to }) => (
            <li key={to} className="list-none">
              <NavLink
                to={to}
                className={({ isActive }) =>
                  `text-lg font-medium transition-colors ${
                    isActive
                      ? 'text-gray-200 border-b border-white/60 pb-1'
                      : 'text-white hover:text-gray-200'
                  }`
                }
              >
                {label}
              </NavLink>
            </li>
          ))}
        </ul>
        <div className="flex items-center gap-3">
          {authLinks.map(({ label, to }) => {
            const Icon = label === 'Login' ? LogIn : UserPlus
            return (
            <button
                key={to}
                className={buttonBase}
                onClick={() => navigate(to)}
              >
                <div className="absolute inset-0 bg-linear-to-r from-blue-600 to-purple-600 opacity-0 group-hover:opacity-100 transition-opacity duration-300" />
                <div className="relative px-5 py-2.5 border border-white/30 rounded-xl backdrop-blur-sm bg-white/10 group-hover:bg-transparent transition-all duration-300">
                  <div className="flex items-center space-x-2">
                    <span className="text-white font-semibold">{label}</span>
                    <Icon className="w-5 h-5 text-white group-hover:translate-x-1 transition-transform duration-300" />
                  </div>
                </div>
              </button>
            )
          })}
        </div>
      </nav>
    </div>
  )
}

export default Navbar
