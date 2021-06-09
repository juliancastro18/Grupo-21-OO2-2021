function generarPDF() {

	const element = document.getElementById("datosPermiso");
	
	html2pdf()
	.set({
		margin: 1,
		filename: 'permiso.pdf',
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