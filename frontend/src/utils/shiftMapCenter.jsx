
export function shiftMapCenter(map, panelOpen, layersOpen, center, zoom) {
    if (!map) return;

    map.invalidateSize();
    if (center) map._baseCenter = center;
    else if (!map._baseCenter) map._baseCenter = map.getCenter();

    const baseCenter = center ?? map._baseCenter;
    // keep the landfill in the part of the map the info panel leaves free: the
    // panel is on the right on wide screens and at the bottom on narrow ones
    // (see InfoPanel.css)
    const size = map.getSize();
    const narrow = size.x < 640;
    const panelWidth = Math.min(512, Math.max(340, size.x * 0.42));
    const offsetX = (panelOpen && !narrow ? panelWidth * 0.58 : 0) + (layersOpen ? -200 : 0);
    const offsetY = panelOpen && narrow ? size.y * 0.75 / 2 : 0;
    const baseZoom = zoom ?? map.getZoom();

    const targetPoint = map.project(baseCenter, baseZoom).add([offsetX, offsetY]);
    const targetLatLng = map.unproject(targetPoint, baseZoom);

    map.flyTo(targetLatLng, baseZoom, { animate: true, duration: 0.5 });

    if (!panelOpen && !layersOpen) map._baseCenter = undefined;
}
