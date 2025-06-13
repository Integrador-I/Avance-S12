"use client";

import { useState } from "react";
import axios from "axios";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Button } from "@/components/ui/button";
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogDescription, DialogFooter } from "@/components/ui/dialog";
import { Empleado } from "./typeEmpleado";

export const FormEmpleado = ({
  setEmpleados,
}: {
  setEmpleados: React.Dispatch<React.SetStateAction<Empleado[]>>;
}) => {
  const [formData, setFormData] = useState({
    nombre: "",
    segundoNombre: "",
    apellidoPaterno: "",
    apellidoMaterno: "",
    dni: "",
    telefono: "",
    email: "",
    contrasena: "",
  });

  const [dialog, setDialog] = useState({
    open: false,
    title: "",
    message: "",
    success: false,
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    try {
      const res = await axios.post("http://localhost:8080/empleados", {
        ...formData,
      });

      setFormData({
        nombre: "",
        segundoNombre: "",
        apellidoPaterno: "",
        apellidoMaterno: "",
        dni: "",
        telefono: "",
        email: "",
        contrasena: "",
      });

      setEmpleados(prev => [...prev, res.data]);

      setDialog({
        open: true,
        title: "Éxito",
        message: "Empleado agregado con éxito.",
        success: true,
      });
    } catch (error: any) {
      setDialog({
        open: true,
        title: "Error",
        message: axios.isAxiosError(error)
          ? error.response?.data?.message || "No se pudo agregar el empleado."
          : "Ocurrió un error inesperado.",
        success: false,
      });
    }
  };

  return (
    <>
      <form onSubmit={handleSubmit} className="space-y-4">
        <div>
          <Label htmlFor="nombre">Nombre</Label>
          <Input type="text" name="nombre" value={formData.nombre} onChange={handleChange} required />
        </div>
        <div>
          <Label htmlFor="segundoNombre">Segundo Nombre</Label>
          <Input type="text" name="segundoNombre" value={formData.segundoNombre} onChange={handleChange} required />
        </div>
        <div>
          <Label htmlFor="apellidoPaterno">Apellido Paterno</Label>
          <Input type="text" name="apellidoPaterno" value={formData.apellidoPaterno} onChange={handleChange} required />
        </div>
        <div>
          <Label htmlFor="apellidoMaterno">Apellido Materno</Label>
          <Input type="text" name="apellidoMaterno" value={formData.apellidoMaterno} onChange={handleChange} required />
        </div>
        <div>
          <Label htmlFor="dni">DNI</Label>
          <Input type="text" name="dni" value={formData.dni} onChange={handleChange} required />
        </div>
        <div>
          <Label htmlFor="telefono">Teléfono</Label>
          <Input type="text" name="telefono" value={formData.telefono} onChange={handleChange} required />
        </div>
        <div>
          <Label htmlFor="email">Email</Label>
          <Input type="email" name="email" value={formData.email} onChange={handleChange} required />
        </div>
        <div>
          <Label htmlFor="contrasena">Contraseña</Label>
          <Input type="password" name="contrasena" value={formData.contrasena} onChange={handleChange} required />
        </div>

        <Button type="submit" className="w-full bg-black text-white py-2 rounded hover:bg-gray-800 transition">
          Guardar
        </Button>
      </form>

      <Dialog open={dialog.open} onOpenChange={(open) => setDialog(prev => ({ ...prev, open }))}>
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
