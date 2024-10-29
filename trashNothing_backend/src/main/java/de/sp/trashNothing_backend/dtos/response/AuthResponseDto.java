package de.sp.trashNothing_backend.dtos.response;

public record AuthResponseDto(
        Long id,
        String name,
        String email,
        String plz,
        String addressStrasse,
        String handynummer
) {
}