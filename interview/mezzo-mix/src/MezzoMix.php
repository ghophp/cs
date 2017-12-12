<?php

declare(strict_types=1);

namespace MezzoMix;

class MezzoMix 
{
	private const COLA = "Cola";
	private const FANTA = "Fanta";
	private const MEZZO_MIX = "Mezzo Mix";

	/**
	 * @param  int
	 * @return string|null
	 */
	private function getSodaTypeByNumber(int $n): ?string 
	{
		if ($n%5 === 0 && $n%3 === 0) {
			return self::MEZZO_MIX;
		} else if ($n%5 === 0) {
			return self::FANTA;
		} else if ($n%3 === 0) {
			return self::COLA;
		}

		return null;
	}

	/**
	 * @param  int
	 * @return string
	 */
	public function formatNumberWithSodaType(int $n): string 
	{
		return trim($n . " " . ($this->getSodaTypeByNumber($n) ?? ""));
	}
}