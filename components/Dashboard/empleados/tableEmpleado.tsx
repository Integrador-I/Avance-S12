
"use client";

import { useEffect, useState } from "react";
import axios from "axios";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
  DialogFooter,
} from "@/components/ui/dialog";
import { Empleado } from "./typeEmpleado";

interface Props {
  empleados: Empleado[];
  setEmpleados: React.Dispatch<React.SetStateAction<Empleado[]>>;
}

export const TableEmpleado = ({ empleados, setEmpleados }: Props) => {
  const [editEmpleado, setEditEmpleado] = useState<Empleado | null>(null);
  const [editData, setEditData] = useState({
    nombre: "",
    segundoNombre: "",
    apellidoPaterno: "",
    apellidoMaterno: "",
    dni: "",
    telefono: "",
    email: "",
    contrasena: "",
  });

  useEffect(() => {
    const fetchEmpleados = async () => {
      try {
        const res = await axios.get("http://localhost:8080/empleados");
        setEmpleados(res.data);
      } catch (err) {
        console.error("Error al obtener empleados", err);
      }
    };

    fetchEmpleados();
  }, [setEmpleados]);

  const handleDelete = async (idcliente: number) => {
    try {
      await axios.delete(`http://localhost:8080/empleados/${idcliente}`);
      setEmpleados(prev => prev.filter(emp => emp.idcliente !== idcliente));
    } catch (err) {
      console.error("Error al eliminar empleado", err);
    }
  };

  const openEditModal = (empleado: Empleado) => {
    setEditEmpleado(empleado);
    setEditData({
      nombre: empleado.nombre,
      segundoNombre: empleado.segundoNombre,
      apellidoPaterno: empleado.apellidoPaterno,
      apellidoMaterno: empleado.apellidoMaterno,
      dni: empleado.dni,
      telefono: empleado.telefono,
      email: empleado.email,
      contrasena: empleado.contrasena,
    });
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setEditData(prev => ({ ...prev, [name]: value }));
  };

  const handleEditSubmit = async () => {
    if (!editEmpleado) return;

    try {
      await axios.put(`http://localhost:8080/empleados/${editEmpleado.idcliente}`, {
        ...editData,
      });

      setEmpleados(prev =>
        prev.map(emp =>
          emp.idcliente === editEmpleado.idcliente ? { ...emp, ...editData } : emp
        )
      );
      setEditEmpleado(null);
    } catch (err) {
      console.error("Error al actualizar empleado", err);
    }
  };

  return (
    <div className="relative shadow-md sm:rounded-lg max-w-full">
      <table className="w-full text-sm text-left text-gray-500">
        <thead className="text-xs text-gray-700 uppercase bg-gray-100">
          <tr>
            <th className="px-4 py-2">ID</th>
            <th className="px-4 py-2">Nombre</th>
            <th className="px-4 py-2">Segundo Nombre</th>
            <th className="px-4 py-2">Apellido Paterno</th>
            <th className="px-4 py-2">Apellido Materno</th>
            <th className="px-4 py-2">DNI</th>
            <th className="px-4 py-2">Correo</th>
            <th className="px-4 py-2">Teléfono</th>
            <th className="px-4 py-2">Contraseña</th>
            <th className="px-4 py-2">Acciones</th>
          </tr>
        </thead>
        <tbody>
          {empleados.map(emp => (
            <tr key={emp.idcliente} className="bg-white border-b hover:bg-gray-50">
              <td className="px-4 py-2">{emp.idcliente}</td>
              <td className="px-4 py-2">{emp.nombre}</td>
              <td className="px-4 py-2">{emp.segundoNombre}</td>
              <td className="px-4 py-2">{emp.apellidoPaterno}</td>
              <td className="px-4 py-2">{emp.apellidoMaterno}</td>
              <td className="px-4 py-2">{emp.dni}</td>
              <td className="px-4 py-2">{emp.email}</td>
              <td className="px-4 py-2">{emp.telefono}</td>
              <td className="px-4 py-2">{emp.contrasena}</td>
              <td className="px-4 py-2 space-x-2">
                <button
                  onClick={() => openEditModal(emp)}
                  className="text-blue-600 hover:underline"
                >
                  Editar
                </button>
                <button
                  onClick={() => handleDelete(emp.idcliente)}
                  className="text-red-600 hover:underline"
                >
                  Eliminar
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      <Dialog open={!!editEmpleado} onOpenChange={() => setEditEmpleado(null)}>
        <DialogContent>
          <DialogHeader>
            <DialogTitle>Editar Empleado</DialogTitle>
          </DialogHeader>
          <div className="space-y-3">
            <Input name="nombre" value={editData.nombre} onChange={handleChange} placeholder="Nombre" />
            <Input name="segundoNombre" value={editData.segundoNombre} onChange={handleChange} placeholder="Segundo Nombre" />
            <Input name="apellidoPaterno" value={editData.apellidoPaterno} onChange={handleChange} placeholder="Apellido Paterno" />
            <Input name="apellidoMaterno" value={editData.apellidoMaterno} onChange={handleChange} placeholder="Apellido Materno" />
            <Input name="dni" value={editData.dni} onChange={handleChange} placeholder="DNI" />
            <Input name="telefono" value={editData.telefono} onChange={handleChange} placeholder="Teléfono" />
            <Input name="email" value={editData.email} onChange={handleChange} placeholder="Correo" />
            <Input name="contrasena" value={editData.contrasena} onChange={handleChange} placeholder="Contraseña" />
          </div>
          <DialogFooter>
            <Button onClick={handleEditSubmit}>Guardar</Button>
            <Button variant="outline" onClick={() => setEditEmpleado(null)}>
              Cancelar
            </Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>
    </div>
  );
};
