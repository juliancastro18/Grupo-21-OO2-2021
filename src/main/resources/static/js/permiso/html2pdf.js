function generarPDF() {

	const element = document.getElementById("datosPermiso")
	const nombre = document.getElementById("nombre").innerText.toLowerCase()
	const apellido = document.getElementById("apellido").innerText.toLowerCase()
	const exp = /\d{2}\/\d{2}\/\d{2}/
	const fecha = document.getElementById("fecha").innerText.match(exp)[0].replaceAll("/", "-")
	
	html2pdf()
	.set({
		margin: 1,
		filename: 'permiso_' + nombre + '_' + apellido + '_' + fecha + '.pdf',
		image: {
			type: 'jpeg',
			quality: 0.98
		},
		html2canvas: {
			scale: 3,
			letterRendering: true,
		},
		jsPDF: {
			unit: "in",
			format: "a4",
			orientation: 'portrait'
		}	
	})
	.from(element)
	.save();

}