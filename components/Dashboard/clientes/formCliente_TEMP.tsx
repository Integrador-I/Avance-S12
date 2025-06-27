"use client";

import { useState } from "react";
import axios from "axios";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Button } from "@/components/ui/button";
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
  DialogDescription,
  DialogFooter,
} from "@/components/ui/dialog";
import { Cliente } from "./typeCliente";

export const FormCliente = ({
  setClientes,
}: {
  setClientes: React.Dispatch<React.SetStateAction<Cliente[]>>;
}) => {
  const [formData, setFormData] = useState<Omit<Cliente, "clienteId" | "fechaRegistro">>({
    nombre: "",
    segundoNombre: "",
    apellido: "",
    apellidoMaterno: "",
    documentoIdentidad: "",
    telefono: "",
    correo: "",
    direccion: "",
    descripcion: "",
    password: "",
  });

  const [dialog, setDialog] = useState({
    open: false,
    title: "",
    message: "",
    success: false,
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    try {
      const res = await axios.post("http://localhost:8080/clientes", formData);

      setFormData({
        nombre: "",
        segundoNombre: "",
        apellido: "",
        apellidoMaterno: "",
        documentoIdentidad: "",
        telefono: "",
        correo: "",
        direccion: "",
        descripcion: "",
        password: "",
      });

      setClientes(prev => [...prev, res.data]);

      setDialog({
        open: true,
        title: "Éxito",
        message: "Cliente agregado con éxito.",
        success: true,
      });
    } catch (error: any) {
      setDialog({
        open: true,
        title: "Error",
        message: axios.isAxiosError(error)
          ? error.response?.data?.message || "No se pudo agregar el cliente."
          : "Ocurrió un error inesperado.",
        success: false,
      });
    }
  };

  return (
    <>
      <form onSubmit={handleSubmit} className="space-y-4">
        {Object.keys(formData).map(field => (
          <div key={field}>
            <Label htmlFor={field}>{field}</Label>
            <Input
              type={field === "password" ? "password" : "text"}
              name={field}
              value={(formData as any)[field]}
              onChange={handleChange}
              required
            />
          </div>
        ))}
        <Button type="submit" className="w-full bg-black text-white py-2 rounded hover:bg-gray-800 transition">
          Guardar
        </Button>
      </form>

      <Dialog open={dialog.open} onOpenChange={open => setDialog(prev => ({ ...prev, open }))}>
        <DialogContent>
          <DialogHeader>
            <DialogTitle className={dialog.success ? "text-green-600" : "text-red-600"}>
              {dialog.title}
            </DialogTitle>
            <DialogDescription>{dialog.message}</DialogDescription>
          </DialogHeader>
          <DialogFooter>
            <Button onClick={() => setDialog(prev => ({ ...prev, open: false }))}>
              Cerrar
            </Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>
    </>
  );
};
 