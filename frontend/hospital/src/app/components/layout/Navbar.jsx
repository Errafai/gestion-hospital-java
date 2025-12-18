import { NavLink, useNavigate } from "react-router-dom";
import { LogIn, UserPlus, Menu, X } from "lucide-react";
import { navLinks, authLinks } from "../../routes/map";
import logo from "../../../assets/images/hospital-building.png";
import { useState } from "react";

export const Navbar = () => {
  const navigate = useNavigate();
  const [isMenuOpen, setIsMenuOpen] = useState(false);

  const buttonBase =
    "group relative overflow-hidden rounded-xl focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-white/70";

  const handleNavClick = (to) => {
    navigate(to);
    setIsMenuOpen(false);
  };

  return (
    <div className="w-full bg-blue-500 shadow-md">
      <div className="flex items-center justify-between px-4 py-0 h-20">
        {/* Logo */}
        <NavLink
          to="/"
          className="text-xl md:text-2xl lg:text-4xl font-bold text-white font-serif flex items-center justify-center whitespace-nowrap"
        >
          <img
            className="py-2 pr-2"
            style={{ height: "80px", width: "auto" }}
            src={logo}
            alt="Hospital Logo"
          />
          <span className="hidden sm:inline">Hospital ENSAS</span>
        </NavLink>

        {/* Desktop Navigation */}
        <nav className="hidden xl:flex items-center gap-6 flex-1 justify-center ml-6">
          <ul className="flex items-center gap-6">
            {navLinks.map(({ label, to }) => (
              <li key={to} className="list-none">
                <NavLink
                  to={to}
                  className={({ isActive }) =>
                    `text-lg font-medium transition-colors ${
                      isActive
                        ? "text-gray-200 border-b border-white/60 pb-1"
                        : "text-white hover:text-gray-200"
                    }`
                  }
                >
                  {label}
                </NavLink>
              </li>
            ))}
          </ul>
        </nav>

        {/* Desktop Auth Buttons */}
        <div className="hidden xl:flex items-center gap-3">
          {authLinks.map(({ label, to }) => {
            const Icon = label === "Login" ? LogIn : UserPlus;
            return (
              <button
                key={to}
                className={buttonBase}
                onClick={() => handleNavClick(to)}
              >
                <div className="absolute inset-0 bg-gradient-to-r from-blue-600 to-purple-600 opacity-0 group-hover:opacity-100 transition-opacity duration-300" />
                <div className="relative px-5 py-2.5 border border-white/30 rounded-xl backdrop-blur-sm bg-white/10 group-hover:bg-transparent transition-all duration-300">
                  <div className="flex items-center space-x-2">
                    <span className="text-white font-semibold">{label}</span>
                    <Icon className="w-5 h-5 text-white group-hover:translate-x-1 transition-transform duration-300" />
                  </div>
                </div>
              </button>
            );
          })}
        </div>

        {/* Mobile Menu Button */}
        <button
          onClick={() => setIsMenuOpen(!isMenuOpen)}
          className="xl:hidden text-white hover:text-gray-200 transition-colors focus:outline-none"
        >
          {isMenuOpen ? <X size={28} /> : <Menu size={28} />}
        </button>
      </div>

      {/* Mobile Menu */}
      {isMenuOpen && (
        <div className="xl:hidden bg-blue-600 border-t border-blue-400 animate-in fade-in slide-in-from-top-2">
          <nav className="px-4 py-4 space-y-2">
            {/* Mobile Navigation Links */}
            <ul className="space-y-2 mb-4">
              {navLinks.map(({ label, to }) => (
                <li key={to}>
                  <NavLink
                    to={to}
                    onClick={() => setIsMenuOpen(false)}
                    className={({ isActive }) =>
                      `block px-4 py-2 rounded-lg transition-colors ${
                        isActive
                          ? "bg-blue-700 text-white font-semibold"
                          : "text-white hover:bg-blue-700"
                      }`
                    }
                  >
                    {label}
                  </NavLink>
                </li>
              ))}
            </ul>

            {/* Divider */}
            <div className="border-t border-blue-400 my-4" />

            {/* Mobile Auth Buttons */}
            <div className="space-y-2 flex flex-col">
              {authLinks.map(({ label, to }) => {
                const Icon = label === "Login" ? LogIn : UserPlus;
                return (
                  <button
                    key={to}
                    onClick={() => handleNavClick(to)}
                    className="w-full flex items-center justify-center gap-2 px-4 py-3 bg-white/10 hover:bg-white/20 text-white font-semibold rounded-lg border border-white/30 transition-colors"
                  >
                    <span>{label}</span>
                    <Icon className="w-5 h-5" />
                  </button>
                );
              })}
            </div>
          </nav>
        </div>
      )}
    </div>
  );
};

export default Navbar;


