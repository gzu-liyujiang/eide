
#include <type_traits>
#include <string>
#include "to_string.hpp"

namespace std{

namespace
{

// as_string

template<typename S, typename P, typename V >
inline
S
as_string(P sprintf_like, S s, const typename S::value_type* fmt, V a)
{
    typedef typename S::size_type size_type;
    size_type available = s.size();
    while (true)
    {
        int status = sprintf_like(&s[0], available + 1, fmt, a);
        if ( status >= 0 )
        {
            size_type used = static_cast<size_type>(status);
            if ( used <= available )
            {
                s.resize( used );
                break;
            }
            available = used; // Assume this is advice of how much space we need.
        }
        else
            available = available * 2 + 1;
        s.resize(available);
    }
    return s;
}

template <class S, class V, bool = is_floating_point<V>::value>
struct initial_string;

template <class V, bool b>
struct initial_string<string, V, b>
{
    __attribute__ ((visibility("hidden"))) 
    string
    operator()() const
    {
        string s;
        s.resize(s.capacity());
        return s;
    }
};

}  // unnamed namespace

__attribute__ ((visibility("hidden")))
string to_string(int val)
{
    return as_string(snprintf, initial_string<string, int>()(), "%d", val);
}
__attribute__ ((visibility("hidden")))
string to_string(unsigned val)
{
    return as_string(snprintf, initial_string<string, unsigned>()(), "%u", val);
}
__attribute__ ((visibility("hidden")))
string to_string(long val)
{
    return as_string(snprintf, initial_string<string, long>()(), "%ld", val);
}
__attribute__ ((visibility("hidden")))
string to_string(unsigned long val)
{
    return as_string(snprintf, initial_string<string, unsigned long>()(), "%lu", val);
}
__attribute__ ((visibility("hidden")))
string to_string(long long val)
{
    return as_string(snprintf, initial_string<string, long long>()(), "%lld", val);
}
__attribute__ ((visibility("hidden")))
string to_string(unsigned long long val)
{
    return as_string(snprintf, initial_string<string, unsigned long long>()(), "%llu", val);
}
__attribute__ ((visibility("hidden")))
string to_string(float val)
{
    return as_string(snprintf, initial_string<string, float>()(), "%f", val);
}
__attribute__ ((visibility("hidden")))
string to_string(double val)
{
    return as_string(snprintf, initial_string<string, double>()(), "%f", val);
}
__attribute__ ((visibility("hidden")))
string to_string(long double val)
{
    return as_string(snprintf, initial_string<string, long double>()(), "%Lf", val);
}
};
