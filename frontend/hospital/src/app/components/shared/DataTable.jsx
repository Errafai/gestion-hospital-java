import React from 'react'

export const DataTable = ({ columns, data }) => {
  return (
    <table className="w-full border-collapse border">
      <thead>
        <tr>
          {columns.map((col) => (
            <th key={col} className="border p-2">{col}</th>
          ))}
        </tr>
      </thead>
      <tbody>
        {data.map((row, idx) => (
          <tr key={idx}>
            {Object.values(row).map((val, i) => (
              <td key={i} className="border p-2">{val}</td>
            ))}
          </tr>
        ))}
      </tbody>
    </table>
  )
}

export default DataTable
