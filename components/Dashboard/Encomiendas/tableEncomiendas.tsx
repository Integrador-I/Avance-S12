"use client";
import React, { useState, useEffect } from "react";
import { Button } from "@/components/ui/button";
import {
  Dialog,
  DialogTrigger,
  DialogContent,
  DialogHeader,
  DialogTitle,
  DialogDescription,
  DialogFooter,
  DialogClose,
} from "@/components/ui/dialog";
import {
  Select,
  SelectContent,
  SelectGroup,
  SelectItem,
  SelectLabel,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";
import { Input } from "@/components/ui/input";

type Status = "pending" | "confirmed" | "paid" | "shipped" | "delivered";

interface Encomienda {
  codigo: string;
  estado: Status;
}

export const TableEncomiendas: React.FC = () => {
  const [encomiendas, setEncomiendas] = useState<Encomienda[]>([]);
  const [tempData, setTempData] = useState<Encomienda>({ codigo: "", estado: "pending" });
  const [originalCodigo, setOriginalCodigo] = useState(""); // para enviar en PUT
  const [isOpen, setIsOpen] = useState(false);

  const statusOptions: { value: Status; label: string }[] = [
    { value: "pending", label: "Pending" },
    { value: "confirmed", label: "Confirmed" },
    { value: "paid", label: "Paid" },
    { value: "shipped", label: "Shipped" },
    { value: "delivered", label: "Delivered" },
  ];

  const statusStyles: Record<Status, string> = {
    pending: "bg-gray-200 text-gray-800",
    confirmed: "bg-blue-200 text-blue-800",
    paid: "bg-green-200 text-green-800",
    shipped: "bg-yellow-200 text-yellow-800",
    delivered: "bg-purple-200 text-purple-800",
  };

  // Cargar datos al inicio
  useEffect(() => {
    fetch("http://localhost:8080/api/seguimiento/lista")
      .then((res) => res.json())
      .then((data) => {
        const formatted: Encomienda[] = data.map((item: any) => ({
          codigo: item.codigo,
          estado: item.estado as Status,
        }));
        setEncomiendas(formatted);
      })
      .catch((err) => {
        console.error("Error al cargar encomiendas:", err);
      });
  }, []);

  const handleTempChange = (field: keyof Encomienda, value: string) => {
    setTempData((prev) => ({
      ...prev,
      [field]: field === "estado" ? (value as Status) : value,
    }));
  };

  const openDialog = (item: Encomienda) => {
    setTempData(item);
    setOriginalCodigo(item.codigo);
    setIsOpen(true);
  };

  const saveChanges = async () => {
    try {
      const res = await fetch("http://localhost:8080/api/seguimiento/actualizar", {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          codigoAnterior: originalCodigo,
          nuevoCodigo: tempData.codigo,
          estado: tempData.estado,
        }),
      });

      if (res.ok) {
        // Actualizar lista local
        setEncomiendas((prev) =>
          prev.map((e) =>
            e.codigo === originalCodigo ? { codigo: tempData.codigo, estado: tempData.estado } : e
          )
        );
      } else {
        const errorText = await res.text();
        alert("Error al guardar: " + errorText);
      }
    } catch (err) {
      console.error("Error de red:", err);
    } finally {
      setIsOpen(false);
    }
  };

  return (
    <Dialog open={isOpen} onOpenChange={setIsOpen}>
      <div className="overflow-x-auto bg-white shadow-dashboard rounded-lg">
        <table className="min-w-full">
          <thead>
            <tr>
              <th className="px-6 py-3 text-left text-sm font-medium text-blue-500">Código</th>
              <th className="px-6 py-3 text-left text-sm font-medium text-blue-500">Estado</th>
              <th className="px-6 py-3"></th>
            </tr>
          </thead>
          <tbody>
            {encomiendas.map((item, idx) => (
              <tr key={idx} className="border-b">
                <td className="px-6 py-4 text-sm text-blue-900">{item.codigo}</td>
                <td className="px-6 py-4">
                  <span
                    className={`inline-block px-3 py-1 text-xs font-semibold rounded-full ${statusStyles[item.estado]}`}
                  >
                    {item.estado.charAt(0).toUpperCase() + item.estado.slice(1)}
                  </span>
                </td>
                <td className="px-6 py-4 text-right">
                  <DialogTrigger asChild>
                    <Button variant="outline" onClick={() => openDialog(item)}>
                      Show Details
                    </Button>
                  </DialogTrigger>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      {/* Diálogo para editar */}
      <DialogContent className="sm:max-w-lg">
        <DialogHeader>
          <DialogTitle>Editar encomienda</DialogTitle>
          <DialogDescription>
            Puedes modificar el código o estado y guardar los cambios.
          </DialogDescription>
        </DialogHeader>

        <div className="space-y-4 mt-2">
          <div>
            <label className="text-sm font-medium">Código</label>
            <Input
              value={tempData.codigo}
              onChange={(e) => handleTempChange("codigo", e.target.value)}
            />
          </div>

          <div>
            <label className="text-sm font-medium">Estado</label>
            <Select
              value={tempData.estado}
              onValueChange={(value) => handleTempChange("estado", value)}
            >
              <SelectTrigger className="w-full">
                <SelectValue placeholder="Seleccionar estado" />
              </SelectTrigger>
              <SelectContent>
                <SelectGroup>
                  <SelectLabel>Estados</SelectLabel>
                  {statusOptions.map((opt) => (
                    <SelectItem key={opt.value} value={opt.value}>
                      {opt.label}
                    </SelectItem>
                  ))}
                </SelectGroup>
              </SelectContent>
            </Select>
          </div>
        </div>

        <DialogFooter>
          <DialogClose asChild>
            <Button variant="outline">Cancelar</Button>
          </DialogClose>
          <DialogClose asChild>
            <Button onClick={saveChanges}>Guardar</Button>
          </DialogClose>
        </DialogFooter>
      </DialogContent>
    </Dialog>
  );
};
