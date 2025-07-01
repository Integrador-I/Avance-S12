import * as React from "react"

import {
  Select,
  SelectContent,
  SelectGroup,
  SelectItem,
  SelectLabel,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select"

export function SelectDemo() {
  return (
    <Select>
      <SelectTrigger className="w-[180px] rounded-md border border-blue-200 bg-white shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500 text-gray-800">
        <SelectValue placeholder="Selecciona una ruta" className="text-gray-700" />
      </SelectTrigger>
      <SelectContent className="w-[180px] max-w-[180px] overflow-hidden z-50 bg-white border border-blue-200 shadow-lg" side="bottom" align="start" sideOffset={4}>
        <SelectGroup>
          <SelectLabel className="text-blue-700 font-medium">Rutas</SelectLabel>
          <SelectItem value="arequipa" className="hover:bg-blue-50 focus:bg-blue-50 text-gray-800">Arequipa</SelectItem>
          <SelectItem value="omate" className="hover:bg-blue-50 focus:bg-blue-50 text-gray-800">Omate</SelectItem>
        </SelectGroup>
      </SelectContent>
    </Select>
  )
}
