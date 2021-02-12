Feature: CMS Features Test

    Scenario: Create A New Page
      Given User navigates to the admin page
      When User fill the admin login form with "admin@phptravels.com" email and "demoadmin" password

      Given User navigates to CMS, "PAGES" section
      When User adds a new page with "Seo" page title, "seoKey" meta keywords, "SEO Description" meta description
      Then User see a new page with "Seo" title is created

    Scenario: Visit the New Page and Check SEO keys
      Given User navigates to "https://www.phptravels.net/seo" page
      Then User see "Seo" as page title, "seoKey" as meta keywords, "SEO Description" as meta description