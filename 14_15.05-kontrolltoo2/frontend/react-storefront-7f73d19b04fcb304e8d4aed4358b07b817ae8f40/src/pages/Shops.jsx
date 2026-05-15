import React, { useEffect, useState } from 'react'

function Shops() {
  const [shops, setShops] = useState([])

  useEffect(() => {
    fetch(import.meta.env.VITE_BACK_URL + "/shops")
      .then(res => res.json())
      .then(json => setShops(json))
  }, [])

  return (
    <div className="flex flex-col gap-6 pt-4">
      <h1 className="text-xl font-semibold">Our shops</h1>
      {shops.map((shop) => (
        <div key={shop.typeID}>
          {shop.typeID} - {shop.type} - {shop.description}
        </div>
      ))}
    </div>
  )
}

export default Shops
