import { NavLink } from "react-router-dom";
import { Menu, PersonStanding, StickerIcon, User, X } from "lucide-react";
import { navDashboard } from "../../routes/map";
// import logo from "../../../assets/images/hospital-building.png";
import { useState } from "react";

export const DashNavbar = () => {
  //   const navigate = useNavigate();
  const [isMenuOpen, setIsMenuOpen] = useState(false);

  //   const handleNavClick = (to) => {
  //     navigate(to);
  //     setIsMenuOpen(false);
  //   };

  const buttonBase =
    "bg-gradient-to-r from-red-400 to-pink-600 px-2 py-2 rounded-lg hover:animate-bounce transition-all";

  return (
    <div className="w-full bg-blue-400 shadow-md">
      <div className="flex items-center justify-between px-4 py-0 h-20">
        {/* Logo */}
        <NavLink
          to="/patients/dashboard"
          className="text-xl md:text-2xl lg:text-4xl font-bold text-white font-serif flex items-center justify-center whitespace-nowrap"
        >
          {/* <img
            className="py-2 pr-2"
            style={{ height: "80px", width: "auto" }}
            src={logo}
            alt="Hospital Logo"
          /> */}

          <StickerIcon className="w-12 h-12 mx-2 bg-gradient-to-r from-red-400 to-pink-600 rounded-lg animate-bounce" />
          <span className="hidden sm:inline">Patient Dashboard</span>
        </NavLink>

        {/* Desktop Navigation */}
        <nav className="hidden xl:flex items-center gap-6 flex-1 justify-end mx-4">
          <ul className="flex items-center gap-6">
            {navDashboard.map(({ label, to }) => (
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
                  {label === "Profile" ? (
                    <div className="w-12 h-12 bg-gray-200 flex items-center justify-center rounded-full text-purple-500">
                      {<User className="w-10 h-10 p-1" />}
                    </div>
                  ) : (
                    <div className={buttonBase}>{label}</div>
                  )}
                </NavLink>
              </li>
            ))}
          </ul>
        </nav>

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
              {navDashboard.map(({ label, to }) => (
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
          </nav>
        </div>
      )}
    </div>
  );
};

export default DashNavbar;
