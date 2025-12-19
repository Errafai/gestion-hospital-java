import React from 'react'

export const Sidebar = ({ links = [], activeTab = '', onLinkClick }) => {
  return (
    <aside className=" w-64 min-h-[calc(100vh-5rem)] bg-gray-50 border-r-2 border-gray-200 px-4 py-6">
      <nav>
        <ul className="space-y-2 mt-4 ">
          {links.map((item, idx) => {
            // Check if this link is currently active
            const isActive = activeTab === item.tabId

            return (
              <li
                key={idx}
                onClick={() => onLinkClick && onLinkClick(item.tabId)}
                className={`px-3 py-3 rounded-lg border border-red-200 cursor-pointer hover:bg-blue-100 text-gray-700 font-medium transition-colors ${isActive ? 'bg-blue-200 border-blue-400 font-semibold' : ''
                  }`}
              >
                {item.label}
              </li>
            )
          })}
        </ul>
      </nav>
    </aside>
  )
}

export default Sidebar
