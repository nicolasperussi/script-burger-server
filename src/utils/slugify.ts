export function slugify(str: string) {
	// Remove leading and trailing whitespace
	str = str.trim();

	// Make the string lowercase
	str = str.toLowerCase();

	// Remove accents, swap ñ for n, etc
	str = str.normalize('NFD').replace(/[\u0300-\u036f]/g, '');

	// Remove invalid characters
	str = str.replace(/[^a-z0-9 -]/g, '');

	// Replace whitespace with a hyphen
	str = str.replace(/\s+/g, '-');

	// Collapse consecutive hyphens
	str = str.replace(/-+/g, '-');

	return str;
}
