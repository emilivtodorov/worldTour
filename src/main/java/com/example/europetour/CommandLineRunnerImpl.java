package com.example.europetour;

import com.example.europetour.model.entity.Country;
import com.example.europetour.model.entity.Neighbours;
import com.example.europetour.repository.CountryRepository;
import com.example.europetour.repository.CurrencyRepository;
import com.example.europetour.repository.NeighboursRepository;
import com.example.europetour.service.CountryService;
import com.example.europetour.service.CurrencyService;
import com.example.europetour.service.NeighboursService;
import com.google.gson.Gson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

@Service
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CountryService countryService;
    private final NeighboursService neighboursService;
    private final CurrencyService currencyService;
    private final CountryRepository countryRepository;
    private final NeighboursRepository neighboursRepository;
    private final CurrencyRepository currencyRepository;

    public CommandLineRunnerImpl(CountryService countryService, Gson gson, NeighboursService neighboursService, CurrencyService currencyService, CountryRepository countryRepository, NeighboursRepository neighboursRepository, CurrencyRepository currencyRepository) {
        this.countryService = countryService;
        this.neighboursService = neighboursService;
        this.currencyService = currencyService;
        this.countryRepository = countryRepository;
        this.neighboursRepository = neighboursRepository;
        this.currencyRepository = currencyRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        seedData();

        System.out.println("Please enter starting country:");
        String startingCountryStr = scanner.nextLine();

        System.out.println("Please enter budget per country:");
        BigDecimal budgetPerCountry = new BigDecimal(scanner.nextLine());

        System.out.println("Please enter total budget:");
        BigDecimal budget = new BigDecimal(scanner.nextLine());

        System.out.println("Please enter currency:");
        String currency = scanner.nextLine();

        Country startingCountry = countryRepository.findCountryByName(startingCountryStr);

        if (startingCountry == null) {
            System.out.println("This is not correct country name");
            return;
        }

        List<Neighbours> allNeighboursByCountry = neighboursRepository.findAllByCountry_Id(startingCountry.getId());

        BigDecimal neighboursCount = new BigDecimal(allNeighboursByCountry.size());

        if (allNeighboursByCountry.size() > 0) {
            System.out.printf("%s has %d neighbour countries:%n", startingCountry.getName(), allNeighboursByCountry.size());
            for (Neighbours neighbours : allNeighboursByCountry) {
                System.out.println(neighbours.getNeighbour().getCode());
            }

            int count = 0;
            while (budget.compareTo(budgetPerCountry.multiply(neighboursCount)) > 0) {
                budget = budget.subtract(budgetPerCountry.multiply(neighboursCount));
                count++;
            }
            if (count > 0) {
                System.out.printf("Angel can travel %d times around them and he will have %.2f %s leftover.%n", count, budget, currency);
            } else {
                System.out.println("Not enough money for a trip.");
            }
            for (Neighbours neighbours : allNeighboursByCountry) {
                BigDecimal currencyValue = currencyCalculator(neighbours.getNeighbour(), currency, count, budgetPerCountry);
                String currencyCode = neighbours.getNeighbour().getCurrency().getCurrencyCode();
                System.out.printf("For %s he will need to buy %.2f %s%n",
                        neighbours.getNeighbour().getName(),
                        currencyValue,
                        currencyCode);
            }
        } else {
            System.out.println("This country has no neighbour countries Angel needs airplane");
        }
    }

    private BigDecimal currencyCalculator(Country country, String currency, int count, BigDecimal budgetPerCountry) {

        String countryCurrencyCode = countryRepository.findCountryByName(country.getName()).getCurrency().getCurrencyCode();
        BigDecimal result;

        BigDecimal amount = new BigDecimal(count).multiply(budgetPerCountry);

        BigDecimal currentCurrencyRateToEuro = currencyRepository.findCurrencyByCurrencyCode(currency).getRateToEuro();

        BigDecimal countryCurrencyRateToEuro = currencyRepository.findCurrencyByCurrencyCode(countryCurrencyCode).getRateToEuro();

        result = amount.divide(currentCurrencyRateToEuro).multiply(countryCurrencyRateToEuro);


        return result;
    }

    private void seedData() throws IOException {
        currencyService.seedCurrency();
        countryService.seedCountries();
        neighboursService.seedNeighbours();
    }
}
